package pl.coderslab.nutrition_planner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.nutrition_planner.dto.DishDto;
import pl.coderslab.nutrition_planner.entities.Dish;
import pl.coderslab.nutrition_planner.exceptions.NotFoundException;
import pl.coderslab.nutrition_planner.services.DishService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/dish")
public class DishController {

  private final DishService dishService;

  @Autowired
  public DishController(DishService dishService) {
    this.dishService = dishService;
  }

  @GetMapping(path = "/{id}")
  public ModelAndView get(@PathVariable Long id, Model model) {

    return new ModelAndView("/dishes/dishDetails", "dish", dishService.find(id));
  }

  @GetMapping("/new")
  public ModelAndView showForm() {
    return new ModelAndView("dishes/dishForm", "dish", new DishDto());
  }

  @PostMapping("/new")
  public String post(@Valid @ModelAttribute("dish") DishDto dishDto, BindingResult result) {
    if (result.hasErrors()) {
      return "dishes/dishForm";

    } else {
      DishDto savedDish = dishService.save(dishDto);

      return "redirect:/dish/" + savedDish.getId();
    }
  }

  @GetMapping("/all")
  public ModelAndView getAll(Model model) {
    return new ModelAndView("dishes/dishList", "dishes", dishService.getAll());
  }

  @GetMapping("/{id}/update")
  private ModelAndView updateForm(Model model, @PathVariable Long id) {

    return new ModelAndView("dishes/dishForm.html", "dish", dishService.find(id));
  }

  @PostMapping("/{id}/update")
  public String update(
      @Valid @ModelAttribute("dish") DishDto dishDto, @PathVariable Long id, BindingResult result) {
    if (result.hasErrors()) {
      return "dishes/dishForm";
    } else {
      dishService.update(dishDto, id);

      return "redirect:/dish/" + dishDto.getId();
    }
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {

    dishService.remove(id);

    return "redirect:/dish/all";
  }

  @RequestMapping("/find")
  public ModelAndView findDishes() {
    return new ModelAndView("dishes/findDish", "dishDto", DishDto.builder().build());
  }

  @GetMapping
  public String processFindDishes(DishDto dishDto, BindingResult result, Model model) {

    if (dishDto.getName() == null) {
      dishDto.setName("");
    }

    List<DishDto> results = dishService.findAllByNameLike(("%" + dishDto.getName() + "%"));

    if (results.isEmpty()) {
      result.rejectValue("name", "notFound", "not found");
      return "dishes/findDish";

    } else if (results.size() == 1) {

      dishDto = results.get(0);
      return "redirect:/dish/" + dishDto.getId();
    } else {

      model.addAttribute("dishes", results);
      return "dishes/dishList";
    }
  }



  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public ModelAndView handleNotFound(Exception exception) {

    ModelAndView modelAndView = new ModelAndView();

    modelAndView.setViewName("errors/404error");
    modelAndView.addObject("exception", exception);

    return modelAndView;
  }
}

package pl.coderslab.nutrition_planner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.nutrition_planner.dto.DishDto;
import pl.coderslab.nutrition_planner.dto.ProductDto;


import pl.coderslab.nutrition_planner.exceptions.NotFoundException;
import pl.coderslab.nutrition_planner.services.DishService;
import pl.coderslab.nutrition_planner.services.ProductService;

import javax.validation.Valid;

import java.util.List;

@Controller
public class ProductController {

  private final ProductService productService;
  private final DishService dishService;

  @Autowired
  public ProductController(ProductService productService,
                           DishService dishService) {
    this.productService = productService;
    this.dishService = dishService;
  }

  @GetMapping(path = "product/{id}")
  public ModelAndView get(@PathVariable Long id, Model model) {

    return new ModelAndView("/products/productDetails", "product", productService.find(id));
  }

  @GetMapping("product/new")
  public ModelAndView showForm() {
    return new ModelAndView("products/productForm", "product", new ProductDto());
  }

  @PostMapping("product/new")
  public String post(
      @Valid @ModelAttribute("product") ProductDto productDto, BindingResult result) {
    if (result.hasErrors()) {
      return "products/productForm";

    } else {
      ProductDto savedProduct = productService.save(productDto);

      return "redirect:/product/" + savedProduct.getId();
    }
  }

  @GetMapping("product/all")
  public ModelAndView getAll(Model model) {
    return new ModelAndView("products/productList", "products", productService.getAll());
  }

  @GetMapping("product/{id}/update")
  private ModelAndView updateForm(Model model, @PathVariable Long id) {

    return new ModelAndView("products/productsForm.html", "product", productService.find(id));
  }

  @PostMapping("product/{id}/update")
  public String update(
      @Valid @ModelAttribute("product") ProductDto productDto,
      @PathVariable Long id,
      BindingResult result) {
    if (result.hasErrors()) {
      return "products/productsForm";
    } else {
      productService.update(productDto, id);

      return "redirect:/product/" + productDto.getId();
    }
  }

  @DeleteMapping(path = "product/{id}")
  public String delete(@PathVariable Long id) {

    productService.remove(id);

    return "redirect:/product/all";
  }

  @RequestMapping("product/find")
  public ModelAndView findProducts() {
    return new ModelAndView("products/findProduct", "productDto", ProductDto.builder().build());
  }

  @GetMapping("/product")
  public String processFindProducts(ProductDto productDto, BindingResult result, Model model) {

    if (productDto.getName() == null) {
      productDto.setName("");
    }

    List<ProductDto> results = productService.findAllByNameLike(("%" + productDto.getName() + "%"));

    if (results.isEmpty()) {
      result.rejectValue("name", "notFound", "not found");
      return "products/findProduct";

    } else if (results.size() == 1) {

      productDto = results.get(0);
      return "redirect:/product/" + productDto.getId();
    } else {

      model.addAttribute("products", results);
      return "products/productList";
    }
  }

  @GetMapping("dish/{id}/products")
  public String listProductsOfDish(@PathVariable Long id, Model model){

    model.addAttribute("dish", dishService.find(id));

    return "products/list";
  }

  @GetMapping("dish/{id}/product/new")
  public String newDish(@PathVariable Long id, Model model){
    
    DishDto dishDto = dishService.find(id);
    model.addAttribute("dish", dishDto);


    return "/products/productForm";
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

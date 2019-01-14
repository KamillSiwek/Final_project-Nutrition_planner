package pl.coderslab.finishingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.finishingproject.dto.CategoryDto;
import pl.coderslab.finishingproject.services.CategoryService;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/category")
public class CategoryController {

  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping(produces = APPLICATION_JSON_VALUE)
  public Result post(@RequestBody CategoryDto categoryDto) {
    if (categoryDto != null) {
      return Result.ok(categoryService.save(categoryDto));
    } else {
      return Result.error("Error");
    }
  }

  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  public Result get(@PathVariable Long id) {
    if (categoryService.find(id) != null) {
      return Result.ok(categoryService.find(id));
    } else {
      return Result.error("Error");
    }
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public Result getAll() {
    return Result.ok(categoryService.getAll());
  }

  @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  public Result put(@RequestBody CategoryDto categoryDto, @PathVariable Long id) {
    if (categoryDto != null) {
      return Result.ok(categoryService.update(categoryDto, id));
    } else {
      return Result.error("Error");
    }
  }

  @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  public Result delete(@PathVariable Long id) {
    if (categoryService.find(id) != null) {
      return Result.ok(categoryService.remove(id));
    } else {
      return Result.error("Error");
    }
  }
}

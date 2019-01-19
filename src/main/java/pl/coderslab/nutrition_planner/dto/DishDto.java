package pl.coderslab.nutrition_planner.dto;

import lombok.*;
import pl.coderslab.nutrition_planner.entities.Dish;
import pl.coderslab.nutrition_planner.entities.DishCategory;
import pl.coderslab.nutrition_planner.entities.Product;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishDto {
  @NotNull
  private Long id;
  @NotNull
  @Size(min = 5, max = 255)
  private String name;

  private String recipeText;
  private DishCategory dishCategories ;
  private Set<Product> products = new HashSet<>();

  @Transient
  public Dish toDish(DishDto dishDto) {
    Dish entity = new Dish();
    entity.setId(id);
    entity.setName(name);
    entity.setRecipeText(recipeText);
    entity.setDishCategories(dishCategories);
    entity.setProducts(products);
    return entity;
  }


  public boolean isNew() {
    return this.id == null;
  }
}

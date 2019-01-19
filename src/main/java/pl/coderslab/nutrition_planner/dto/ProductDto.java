package pl.coderslab.nutrition_planner.dto;

import lombok.*;
import pl.coderslab.nutrition_planner.entities.Dish;
import pl.coderslab.nutrition_planner.entities.ProductCategory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
  @NotNull
  private Long id;
  @NotNull
  @Size(min = 5, max = 255)
  private String name;
  private Dish dish;
  private Set<ProductCategory> productCategory= new HashSet<>();;
  private Double kcal;
  private Double proteins;
  private Double fats;
  private Double carbs;

  public boolean isNew() {
    return this.id == null;
  }
}

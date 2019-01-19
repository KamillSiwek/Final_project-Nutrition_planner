package pl.coderslab.nutrition_planner.entities;

import lombok.*;
import pl.coderslab.nutrition_planner.dto.ProductDto;

import javax.persistence.*;
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
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Size(min = 5, max = 255)
  private String name;

  @ManyToOne private Dish dish;


  @ManyToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id")
  private Set<ProductCategory> productCategory = new HashSet<>();

  private Double kcal;
  private Double proteins;
  private Double fats;
  private Double carbs;

  @Transient
  public ProductDto toDto() {
    ProductDto dto = new ProductDto();
    dto.setId(this.getId());
    dto.setName(this.getName());
    dto.setDish(this.dish);
    dto.setProductCategory(this.getProductCategory());
    return dto;
  }

  public boolean isNew() {

    return this.id == null;
  }
}

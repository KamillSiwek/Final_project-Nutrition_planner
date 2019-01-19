package pl.coderslab.nutrition_planner.entities;

import lombok.*;
import pl.coderslab.nutrition_planner.dto.DishDto;

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
public class Dish {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Size(min = 5, max = 255)
  private String name;

  @Lob private String recipeText;

  @Enumerated(value = EnumType.STRING)
  private DishCategory dishCategories ;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "dish_products",
      joinColumns = @JoinColumn(name = "dish_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private Set<Product> products = new HashSet<>();

  @Transient
  public DishDto toDto() {
    DishDto dto = new DishDto();
    dto.setId(this.getId());
    dto.setName(this.getName());
    dto.setRecipeText(this.getRecipeText());
    dto.setDishCategories(this.getDishCategories());
    dto.setProducts(this.getProducts());

    return dto;
  }

  public boolean isNew() {
    return this.id == null;
  }

    public Dish addProduct(Product product){
        product.setDish(this);
        this.products.add(product);
        return this;
    }
}

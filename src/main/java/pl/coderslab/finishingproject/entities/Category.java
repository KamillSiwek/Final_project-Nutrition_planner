package pl.coderslab.finishingproject.entities;

import lombok.Data;
import pl.coderslab.finishingproject.dto.CategoryDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private String description;
      @ManyToMany(mappedBy = "category")
      private List<Dish> dishes = new ArrayList<>();



  @Transient
  public CategoryDto toDto() {
    CategoryDto dto = new CategoryDto();
    dto.setId(this.getId());
    return dto;
  }
}

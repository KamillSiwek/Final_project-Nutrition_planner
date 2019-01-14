package pl.coderslab.finishingproject.entities;

import lombok.Data;
import pl.coderslab.finishingproject.dto.DishDto;

import javax.persistence.*;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String recipeText;
    @ManyToMany
    private List<Category> category = new ArrayList<>();




    @Transient
    public DishDto toDto() {
        DishDto dto = new DishDto();
        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setRecipeText(this.getRecipeText());

        return dto;
    }
}

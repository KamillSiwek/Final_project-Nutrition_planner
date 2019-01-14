package pl.coderslab.finishingproject.dto;

import lombok.Data;
import pl.coderslab.finishingproject.entities.Category;
import pl.coderslab.finishingproject.entities.Dish;

import java.beans.Transient;

@Data
public class DishDto {
    private Long id;
    private String name;
    private String recipeText;
    private Category category;


    @Transient
    public Dish toDish(DishDto dishDto) {
        Dish entity = new Dish();
        entity.setId(id);
        entity.setName(name);
        entity.setRecipeText(recipeText);
//        entity.setCategory(category);
        return entity;
    }
}

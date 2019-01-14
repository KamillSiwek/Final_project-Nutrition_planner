package pl.coderslab.finishingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.finishingproject.dto.DishDto;
import pl.coderslab.finishingproject.entities.Dish;
import pl.coderslab.finishingproject.repositories.CategoryRepository;
import pl.coderslab.finishingproject.repositories.DishRepository;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DishService implements BaseService<DishDto, Long> {

    private final DishRepository dishRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public DishService(DishRepository dishRepository, CategoryRepository categoryRepository) {
        this.dishRepository = dishRepository;
        this.categoryRepository =categoryRepository;
    }

    @Override
    public DishDto save(DishDto dto) {
        Dish dish = new Dish();
        dish.setName(dto.getName());
        dish.setRecipeText(dto.getRecipeText());
//        dish.setCategory(categoryRepository.findCategoryByName(dto.getCategory().getName()));
        dishRepository.save(dish);
        return dish.toDto();
    }

    @Override
    public DishDto update(DishDto dto, Long id) {
        Dish dish = dishRepository.findDishById(id);
        dish.setName(dto.getName());
        dish.setRecipeText(dto.getRecipeText());
//        dish.setCategory(categoryRepository.findCategoryByName(dto.getCategory().getName()));
        dishRepository.save(dish);
        return dish.toDto();
    }

    @Override
    public DishDto find(Long id) {
        Dish dish = dishRepository.findDishById(id);
        if (Objects.isNull(dish)) {
            return null;
        }else {
            return dish.toDto();
        }

    }

    @Override
    public Boolean remove(Long id) {
        Dish dish = dishRepository.findDishById(id);
        dishRepository.delete(dish);
        return true;
    }

    @Override
    public Collection<DishDto> getAll() {
        return dishRepository
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(Dish::toDto)
                .collect(Collectors.toList());
    }
}

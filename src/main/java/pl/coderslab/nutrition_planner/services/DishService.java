package pl.coderslab.nutrition_planner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.nutrition_planner.dto.DishDto;
import pl.coderslab.nutrition_planner.entities.Dish;
import pl.coderslab.nutrition_planner.exceptions.NotFoundException;
import pl.coderslab.nutrition_planner.repositories.DishRepository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DishService implements BaseService<DishDto, Long> {

  private final DishRepository dishRepository;


  @Autowired
  public DishService(DishRepository dishRepository) {

    this.dishRepository = dishRepository;
  }

  @Override
  public DishDto save(DishDto dto) {

    Dish dish = new Dish();
    dish.setName(dto.getName());
    dish.setRecipeText(dto.getRecipeText());
    dish.setDishCategories(dto.getDishCategories());
    dish.setProducts(dto.getProducts());
    dishRepository.save(dish);
    return dish.toDto();
  }

  @Override
  public DishDto update(DishDto dto, Long id) {
    Dish dish = dishRepository.findDishById(id);
    if (Objects.isNull(dish)) {
      throw new NotFoundException("Dish Not Found. For ID value: " + id.toString());
    } else {
      dish.setName(dto.getName());
      dish.setRecipeText(dto.getRecipeText());
      dish.setDishCategories(dto.getDishCategories());
      dish.setProducts(dto.getProducts());
      dishRepository.save(dish);
      return dish.toDto();
    }
  }

  @Override
  public DishDto find(Long id) {
    Dish dish = dishRepository.findDishById(id);
    if (Objects.isNull(dish)) {
      throw new NotFoundException("Dish Not Found. For ID value: " + id.toString());
    } else {
      return dish.toDto();
    }
  }

  @Override
  public Boolean remove(Long id) {
    dishRepository.delete(dishRepository.findDishById(id));
    return true;
  }

  @Override
  public Collection<DishDto> getAll() {
    return dishRepository.findAll().stream()
        .filter(Objects::nonNull)
        .map(Dish::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<DishDto> findAllByNameLike(String name) {

    return dishRepository.findAllByNameLike(name).stream()
        .map(Dish::toDto)
        .collect(Collectors.toList());
  }
}

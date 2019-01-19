package pl.coderslab.nutrition_planner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.nutrition_planner.entities.Dish;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {

  Dish findDishById(Long id);

  List<Dish> findAllByNameLike(String name);
}

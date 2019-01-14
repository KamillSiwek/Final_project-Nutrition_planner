package pl.coderslab.finishingproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.finishingproject.entities.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {

    Dish findDishById(Long id);
}

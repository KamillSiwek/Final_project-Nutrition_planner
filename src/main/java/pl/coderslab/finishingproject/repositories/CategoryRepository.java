package pl.coderslab.finishingproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.finishingproject.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  Category findCategoryById(Long id);

  Category findCategoryByName(String name);
}

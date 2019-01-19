package pl.coderslab.nutrition_planner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.nutrition_planner.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long id);

    List<Product> findAllByNameLike(String name);
}
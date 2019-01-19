package pl.coderslab.nutrition_planner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.nutrition_planner.dto.ProductDto;
import pl.coderslab.nutrition_planner.entities.Product;
import pl.coderslab.nutrition_planner.repositories.ProductRepository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService implements BaseService<ProductDto, Long> {

  private final ProductRepository productRepository;
//  private final CategoryRepository categoryRepository;

  @Autowired
  public ProductService(
      ProductRepository productRepository) {
    this.productRepository = productRepository;
//    this.categoryRepository = categoryRepository;
  }

  @Override
  public ProductDto save(ProductDto dto) {
    Product product = new Product();
    product.setName(dto.getName());
    productRepository.save(product);
    return product.toDto();
  }

  @Override
  public ProductDto update(ProductDto dto, Long id) {
    Product product = productRepository.findProductById(id);
    product.setName(dto.getName());
    productRepository.save(product);
    return product.toDto();
  }

  @Override
  public ProductDto find(Long id) {
    Product product = productRepository.findProductById(id);
    if (Objects.isNull(product)) {
      return null;
    } else {
      return product.toDto();
    }
  }

  @Override
  public Boolean remove(Long id) {
    Product product = productRepository.findProductById(id);
    productRepository.delete(product);
    return true;
  }

  @Override
  public Collection<ProductDto> getAll() {
    return productRepository
        .findAll()
        .stream()
        .filter(Objects::nonNull)
        .map(Product::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<ProductDto> findAllByNameLike(String name) {
    return productRepository.findAllByNameLike(name).stream().map(Product::toDto).collect(Collectors.toList());
  }
}

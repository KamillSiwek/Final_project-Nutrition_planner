package pl.coderslab.finishingproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.finishingproject.dto.CategoryDto;
import pl.coderslab.finishingproject.entities.Category;
import pl.coderslab.finishingproject.repositories.CategoryRepository;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryService implements BaseService<CategoryDto, Long> {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        categoryRepository.save(category);
        return category.toDto();
    }

    @Override
    public CategoryDto update(CategoryDto dto, Long id) {
        Category category = categoryRepository.findCategoryById(id);
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        categoryRepository.save(category);
        return category.toDto();
    }

    @Override
    public CategoryDto find(Long id) {
        Category category = categoryRepository.findCategoryById(id);
        if (Objects.isNull(category)) {
            return null;
        }else {
            return category.toDto();
        }

    }

    @Override
    public Boolean remove(Long id) {
        Category category = categoryRepository.findCategoryById(id);
        categoryRepository.delete(category);
        return true;
    }

    @Override
    public Collection<CategoryDto> getAll() {
        return categoryRepository
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(Category::toDto)
                .collect(Collectors.toList());
    }
}

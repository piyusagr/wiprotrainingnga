package com.example.service;

import com.example.entity.Category;
import com.example.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

//	category repository injection
    private final CategoryRepository categoryRepository;

//    get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

//    create new category
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

//    get category by id
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

//    delete category by id
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    
}

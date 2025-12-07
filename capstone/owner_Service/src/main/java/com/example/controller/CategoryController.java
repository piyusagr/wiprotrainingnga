package com.example.controller;

import com.example.entity.Category;
import com.example.service.CategoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owner/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(value="/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> listCategories() {
		List<Category> categories = categoryService.getAllCategories();
		return ResponseEntity.ok(categories);
	}
    
    @GetMapping
    public String getAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/list-category"; // create a thymeleaf template for category list
    }

    @GetMapping("/create")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/create-category"; // thymeleaf form
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute Category category) {
        categoryService.createCategory(category);
        return "redirect:/owner/apps/new";
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/owner/categories";
    }

}

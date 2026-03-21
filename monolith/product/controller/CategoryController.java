package com.ecommerce.monolith.product.controller;

import com.ecommerce.monolith.product.model.Category;
import com.ecommerce.monolith.product.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // CREATE CATEGORY
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    // GET ALL CATEGORIES
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}


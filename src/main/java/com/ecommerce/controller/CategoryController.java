package com.ecommerce.controller;

import com.ecommerce.dto.CategoryRequest;
import com.ecommerce.dto.CategoryResponse;
import com.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // ADMIN only
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public CategoryResponse create( @Valid @RequestBody CategoryRequest request) {
        return categoryService.create(request);
    }

    // PUBLIC
    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryService.getAll();
    }
}

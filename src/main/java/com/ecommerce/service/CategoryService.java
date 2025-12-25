package com.ecommerce.service;

import com.ecommerce.dto.CategoryRequest;
import com.ecommerce.dto.CategoryResponse;

import java.util.List;

public interface  CategoryService {
    CategoryResponse create(CategoryRequest request);
    List<CategoryResponse> getAll();
}

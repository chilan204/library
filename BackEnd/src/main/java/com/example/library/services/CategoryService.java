package com.example.library.services;

import com.example.library.dto.request.CategoryRequestDTO;
import com.example.library.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getAllCategories();

    CategoryResponseDTO getCategoryById(Long id);

    CategoryResponseDTO createCategory(CategoryRequestDTO categoryDTO);

    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryDTO);

    void deleteCategory(Long id);
}

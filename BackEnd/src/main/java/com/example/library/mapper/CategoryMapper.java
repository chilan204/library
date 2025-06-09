package com.example.library.mapper;

import com.example.library.dto.request.CategoryRequestDTO;
import com.example.library.dto.response.CategoryResponseDTO;
import com.example.library.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    public CategoryResponseDTO toResponseDTO(Category entity) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
} 
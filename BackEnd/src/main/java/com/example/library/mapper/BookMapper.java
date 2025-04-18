package com.example.library.mapper;

import com.example.library.dto.request.BookRequestDTO;
import com.example.library.dto.response.BookResponseDTO;
import com.example.library.dto.response.CategoryResponseDTO;
import com.example.library.entities.Book;
import com.example.library.entities.Category;
import com.example.library.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public BookMapper(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public Book toEntity(BookRequestDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublicationYear(dto.getPublicationYear());
        book.setIsbn(dto.getIsbn());
        book.setImage(dto.getImage());
        book.setCreatedBy("admin"); // Mặc định created_by là admin
        
        // Lấy category từ database
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        book.setCategory(category);
        
        return book;
    }

    public BookResponseDTO toResponseDTO(Book entity) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setAuthor(entity.getAuthor());
        dto.setPublicationYear(entity.getPublicationYear());
        dto.setIsbn(entity.getIsbn());
        dto.setImage(entity.getImage());
        
        // Chuyển đổi Category entity sang CategoryResponseDTO
        Category category = entity.getCategory();
        CategoryResponseDTO categoryDTO = categoryMapper.toResponseDTO(category);
        dto.setCategory(categoryDTO);
        
        return dto;
    }
}
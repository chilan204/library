package com.example.library.mapper;

import com.example.library.dto.request.BookRequestDTO;
import com.example.library.dto.response.AuthorResponseDTO;
import com.example.library.dto.response.BookResponseDTO;
import com.example.library.dto.response.CategoryResponseDTO;
import com.example.library.entities.Author;
import com.example.library.entities.Book;
import com.example.library.entities.Category;
import com.example.library.repositories.AuthorRepository;
import com.example.library.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public BookMapper(CategoryRepository categoryRepository, CategoryMapper categoryMapper, AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public Book toEntity(BookRequestDTO dto) {
        Book book = new Book();
        book.setName(dto.getName());
        book.setDescription(dto.getDescription());

        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        book.setAuthor(author);

        book.setPublicationYear(dto.getPublicationYear());
        book.setIsbn(dto.getIsbn());
        book.setImage(dto.getImage());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        book.setCategory(category);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        book.setCreatedBy(currentUsername);
        
        return book;
    }

    public BookResponseDTO toResponseDTO(Book entity) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());

        Author author = entity.getAuthor();
        AuthorResponseDTO authorDTO = authorMapper.toResponseDTO(author);
        dto.setAuthor(authorDTO);

        dto.setPublicationYear(entity.getPublicationYear());
        dto.setIsbn(entity.getIsbn());
        dto.setImage(entity.getImage());
        
        Category category = entity.getCategory();
        CategoryResponseDTO categoryDTO = categoryMapper.toResponseDTO(category);
        dto.setCategory(categoryDTO);
        
        return dto;
    }
}
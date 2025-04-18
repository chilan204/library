package com.example.library.mapper;

import com.example.library.dto.request.BookRequestDTO;
import com.example.library.dto.response.BookResponseDTO;
import com.example.library.entities.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book toEntity(BookRequestDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublicationYear(dto.getPublicationYear());
        book.setIsbn(dto.getIsbn());
        book.setImage(dto.getImage());
        book.setCategoryId(dto.getCategoryId());
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
        dto.setCategoryId(entity.getCategoryId());
        return dto;
    }
} 
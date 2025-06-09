package com.example.library.mapper;

import com.example.library.dto.request.AuthorRequestDTO;
import com.example.library.dto.response.AuthorResponseDTO;
import com.example.library.entities.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public Author toEntity(AuthorRequestDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setDescription(dto.getDescription());
        author.setBirthDate(dto.getBirthDate());
        return author;
    }

    public AuthorResponseDTO toResponseDTO(Author entity) {
        AuthorResponseDTO dto = new AuthorResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setBirthDate(entity.getBirthDate());
        return dto;
    }
} 
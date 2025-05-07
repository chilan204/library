package com.example.library.services;

import com.example.library.dto.request.AuthorRequestDTO;
import com.example.library.dto.response.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {
    List<AuthorResponseDTO> getAllAuthors();

    AuthorResponseDTO getAuthorById(Long id);

    AuthorResponseDTO createAuthor(AuthorRequestDTO AuthorDTO);

    AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO AuthorDTO);

    void deleteAuthor(Long id);
}

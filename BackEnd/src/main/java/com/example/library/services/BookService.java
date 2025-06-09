package com.example.library.services;

import com.example.library.dto.request.BookRequestDTO;
import com.example.library.dto.response.BookResponseDTO;

import java.util.List;

public interface BookService {
    List<BookResponseDTO> getAllBooks();

    BookResponseDTO getBookById(Long id);

    List<BookResponseDTO> getBooksByCategoryId(Long categoryId);

    List<BookResponseDTO> getBooksByAuthorId(Long authorId);

    BookResponseDTO createBook(BookRequestDTO bookDTO);

    BookResponseDTO updateBook(Long id, BookRequestDTO bookDTO);

    void deleteBook(Long id);
}

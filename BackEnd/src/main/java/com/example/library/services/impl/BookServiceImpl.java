package com.example.library.services.impl;

import com.example.library.dto.request.BookRequestDTO;
import com.example.library.dto.response.BookResponseDTO;
import com.example.library.entities.Book;
import com.example.library.entities.Category;
import com.example.library.mapper.BookMapper;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.CategoryRepository;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return book != null ? bookMapper.toResponseDTO(book) : null;
    }

    @Override
    public List<BookResponseDTO> getBooksByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return bookRepository.findByCategory(category).stream()
                .map(bookMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toResponseDTO(savedBook);
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookRequestDTO bookDTO) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setTitle(bookDTO.getTitle());
            book.setAuthor(bookDTO.getAuthor());
            book.setPublicationYear(bookDTO.getPublicationYear());
            book.setIsbn(bookDTO.getIsbn());
            book.setImage(bookDTO.getImage());
            
            Category category = categoryRepository.findById(bookDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            book.setCategory(category);
            
            Book updatedBook = bookRepository.save(book);
            return bookMapper.toResponseDTO(updatedBook);
        }
        return null;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}

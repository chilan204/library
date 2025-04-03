package com.example.library.services;

import com.example.library.entities.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Long id);

    List<Book> getBooksByCategoryId(Long categoryId);

    Book createBook(Book book);

    Book updateBook(Long id, Book updatedBook);

    void deleteBook(Long id);
}

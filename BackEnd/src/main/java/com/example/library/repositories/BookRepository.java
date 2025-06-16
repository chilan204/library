package com.example.library.repositories;

import com.example.library.entities.Author;
import com.example.library.entities.Book;
import com.example.library.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderByCreatedDateDesc();
    List<Book> findByCategory(Category category);
    List<Book> findByAuthor(Author author);
    List<Book> findByNameContainingIgnoreCaseOrderByCreatedDateDesc(String keyword);
}
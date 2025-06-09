package com.example.library.controllers;

import com.example.library.dto.request.AuthorRequestDTO;
import com.example.library.dto.response.AuthorResponseDTO;
import com.example.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id) {
        AuthorResponseDTO author = authorService.getAuthorById(id);
        return author != null ? ResponseEntity.ok(author) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorRequestDTO authorDTO) {
        return ResponseEntity.ok(authorService.createAuthor(authorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequestDTO authorDTO) {
        AuthorResponseDTO updatedAuthor = authorService.updateAuthor(id, authorDTO);
        return updatedAuthor != null ? ResponseEntity.ok(updatedAuthor) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}

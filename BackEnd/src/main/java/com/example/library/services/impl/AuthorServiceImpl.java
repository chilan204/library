package com.example.library.services.impl;

import com.example.library.dto.request.AuthorRequestDTO;
import com.example.library.dto.response.AuthorResponseDTO;
import com.example.library.entities.Author;
import com.example.library.mapper.AuthorMapper;
import com.example.library.repositories.AuthorRepository;
import com.example.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository AuthorRepository, AuthorMapper AuthorMapper) {
        this.authorRepository = AuthorRepository;
        this.authorMapper = AuthorMapper;
    }

    @Override
    public List<AuthorResponseDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        return author != null ? authorMapper.toResponseDTO(author) : null;
    }

    @Override
    public AuthorResponseDTO createAuthor(AuthorRequestDTO AuthorDTO) {
        Author author = authorMapper.toEntity(AuthorDTO);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toResponseDTO(savedAuthor);
    }

    @Override
    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO AuthorDTO) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            author.setName(AuthorDTO.getName());
            author.setDescription(AuthorDTO.getDescription());
            author.setBirthDate(AuthorDTO.getBirthDate());
            Author updatedAuthor = authorRepository.save(author);
            return authorMapper.toResponseDTO(updatedAuthor);
        }
        return null;
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}

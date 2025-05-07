package com.example.library.dto.response;

import lombok.Data;

@Data
public class BookResponseDTO {
    private Long id;
    private String name;
    private String description;
    private AuthorResponseDTO author;
    private Integer publicationYear;
    private String isbn;
    private String image;
    private CategoryResponseDTO category;
}
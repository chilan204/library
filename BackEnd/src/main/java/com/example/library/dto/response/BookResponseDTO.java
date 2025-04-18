package com.example.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {
    private Long id;
    private String title;
    private String author;
    private Integer publicationYear;
    private String isbn;
    private String image;
    private CategoryResponseDTO category;
}
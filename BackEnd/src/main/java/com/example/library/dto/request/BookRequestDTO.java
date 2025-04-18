package com.example.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotNull(message = "Publication year is required")
    @Positive(message = "Publication year must be positive")
    private Integer publicationYear;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotBlank(message = "Image URL is required")
    private String image;

    @NotNull(message = "Category ID is required")
    @Positive(message = "Category ID must be positive")
    private Long categoryId;
} 
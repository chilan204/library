package com.example.library.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate birthDate;
} 
package com.example.library.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Date birthDate;
} 
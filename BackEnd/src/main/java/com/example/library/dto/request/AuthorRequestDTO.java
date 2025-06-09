package com.example.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorRequestDTO {
    @NotBlank(message = "Tên thể loại không được để trống")
    private String name;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotBlank(message = "Năm sinh không được để trống")
    private LocalDate birthDate;
} 
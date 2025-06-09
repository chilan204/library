package com.example.library.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowingResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private BookResponseDTO book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;
} 
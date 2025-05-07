package com.example.library.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowingResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private BookResponseDTO book;
    private LocalDateTime borrowDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private String status;
} 
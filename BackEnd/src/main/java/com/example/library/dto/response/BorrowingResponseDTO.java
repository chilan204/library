package com.example.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private BookResponseDTO book;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private String status;
} 
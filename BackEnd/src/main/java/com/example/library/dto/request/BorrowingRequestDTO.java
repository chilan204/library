package com.example.library.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowingRequestDTO {
    @NotNull(message = "ID người dùng không được để trống")
    private Long userId;

    @NotNull(message = "ID sách không được để trống")
    private Long bookId;

    @NotNull(message = "Ngày mượn không được để trống")
    private LocalDate borrowDate;

    @NotNull(message = "Ngày hẹn trả không được để trống")
    private LocalDate dueDate;

    private LocalDate returnDate;
} 
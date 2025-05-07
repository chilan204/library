package com.example.library.dto.request;

import com.example.library.enums.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowingRequestDTO {
    @NotNull(message = "ID người dùng không được để trống")
    @Positive(message = "ID người dùng phải là số dương")
    private Long userId;

    @NotNull(message = "ID sách không được để trống")
    @Positive(message = "ID sách phải là số dương")
    private Long bookId;

    @NotNull(message = "Ngày mượn không được để trống")
    private LocalDateTime borrowDate;

    @NotNull(message = "Ngày hẹn trả không được để trống")
    private LocalDateTime dueDate;

    private LocalDateTime returnDate;

    @NotNull(message = "Trạng thái không được để trống")
    private Status status;
} 
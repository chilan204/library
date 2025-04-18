package com.example.library.mapper;

import com.example.library.dto.request.BorrowingRequestDTO;
import com.example.library.dto.response.BorrowingResponseDTO;
import com.example.library.entities.Borrowing;
import org.springframework.stereotype.Component;

@Component
public class BorrowingMapper {
    public Borrowing toEntity(BorrowingRequestDTO dto) {
        Borrowing borrowing = new Borrowing();
        borrowing.setUserId(dto.getUserId());
        borrowing.setBookId(dto.getBookId());
        borrowing.setBorrowDate(dto.getBorrowDate());
        borrowing.setDueDate(dto.getDueDate());
        borrowing.setReturnDate(dto.getReturnDate());
        borrowing.setStatus(dto.getStatus());
        return borrowing;
    }

    public BorrowingResponseDTO toResponseDTO(Borrowing entity) {
        BorrowingResponseDTO dto = new BorrowingResponseDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setBookId(entity.getBookId());
        dto.setBorrowDate(entity.getBorrowDate());
        dto.setDueDate(entity.getDueDate());
        dto.setReturnDate(entity.getReturnDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }
} 
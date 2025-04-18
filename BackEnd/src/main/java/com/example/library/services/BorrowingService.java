package com.example.library.services;

import com.example.library.dto.request.BorrowingRequestDTO;
import com.example.library.dto.response.BorrowingResponseDTO;

import java.util.List;

public interface BorrowingService {
    List<BorrowingResponseDTO> getAllBorrowings();

    BorrowingResponseDTO getBorrowingById(Long id);

    List<BorrowingResponseDTO> getBorrowingsByUserId(Long userId);

    List<BorrowingResponseDTO> getBorrowingsByBookId(Long bookId);

    BorrowingResponseDTO createBorrowing(BorrowingRequestDTO borrowingDTO);

    BorrowingResponseDTO updateBorrowing(Long id, BorrowingRequestDTO borrowingDTO);

    void deleteBorrowing(Long id);
}

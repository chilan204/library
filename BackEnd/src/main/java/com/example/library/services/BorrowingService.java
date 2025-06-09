package com.example.library.services;

import com.example.library.dto.request.BorrowingRequestDTO;
import com.example.library.dto.response.BorrowingResponseDTO;

import java.util.List;

public interface BorrowingService {
    List<BorrowingResponseDTO> getAllBorrowings();

    BorrowingResponseDTO getBorrowingById(Long id);

    List<BorrowingResponseDTO> getBorrowingsOfCurrentUser();

    List<BorrowingResponseDTO> getBorrowingsByUserName(String username);

    List<BorrowingResponseDTO> getBorrowingsByBookName(String name);

    BorrowingResponseDTO createBorrowing(BorrowingRequestDTO borrowingDTO);

    BorrowingResponseDTO updateBorrowing(Long id, BorrowingRequestDTO borrowingDTO);

    void deleteBorrowing(Long id);
}

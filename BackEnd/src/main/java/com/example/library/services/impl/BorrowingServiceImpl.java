package com.example.library.services.impl;

import com.example.library.dto.request.BorrowingRequestDTO;
import com.example.library.dto.response.BorrowingResponseDTO;
import com.example.library.entities.Borrowing;
import com.example.library.mapper.BorrowingMapper;
import com.example.library.repositories.BorrowingRepository;
import com.example.library.services.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingServiceImpl implements BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final BorrowingMapper borrowingMapper;

    @Autowired
    public BorrowingServiceImpl(BorrowingRepository borrowingRepository, BorrowingMapper borrowingMapper) {
        this.borrowingRepository = borrowingRepository;
        this.borrowingMapper = borrowingMapper;
    }

    @Override
    public List<BorrowingResponseDTO> getAllBorrowings() {
        return borrowingRepository.findAll().stream()
                .map(borrowingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BorrowingResponseDTO getBorrowingById(Long id) {
        Borrowing borrowing = borrowingRepository.findById(id).orElse(null);
        return borrowing != null ? borrowingMapper.toResponseDTO(borrowing) : null;
    }

    @Override
    public List<BorrowingResponseDTO> getBorrowingsByUserId(Long userId) {
        return borrowingRepository.findByUserId(userId).stream()
                .map(borrowingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BorrowingResponseDTO> getBorrowingsByBookId(Long bookId) {
        return borrowingRepository.findByBookId(bookId).stream()
                .map(borrowingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BorrowingResponseDTO createBorrowing(BorrowingRequestDTO borrowingDTO) {
        Borrowing borrowing = borrowingMapper.toEntity(borrowingDTO);
        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        return borrowingMapper.toResponseDTO(savedBorrowing);
    }

    @Override
    public BorrowingResponseDTO updateBorrowing(Long id, BorrowingRequestDTO borrowingDTO) {
        Borrowing borrowing = borrowingRepository.findById(id).orElse(null);
        if (borrowing != null) {
            borrowing.setReturnDate(borrowingDTO.getReturnDate());
            borrowing.setStatus(borrowingDTO.getStatus());
            Borrowing updatedBorrowing = borrowingRepository.save(borrowing);
            return borrowingMapper.toResponseDTO(updatedBorrowing);
        }
        return null;
    }

    @Override
    public void deleteBorrowing(Long id) {
        borrowingRepository.deleteById(id);
    }
}

package com.example.library.services.impl;

import com.example.library.dto.request.BorrowingRequestDTO;
import com.example.library.dto.response.BorrowingResponseDTO;
import com.example.library.entities.Borrowing;
import com.example.library.mapper.BorrowingMapper;
import com.example.library.repositories.BorrowingRepository;
import com.example.library.repositories.UserRepository;
import com.example.library.services.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingServiceImpl implements BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final BorrowingMapper borrowingMapper;
    private final UserRepository userRepository;


    @Autowired
    public BorrowingServiceImpl(BorrowingRepository borrowingRepository, BorrowingMapper borrowingMapper, UserRepository userRepository) {
        this.borrowingRepository = borrowingRepository;
        this.borrowingMapper = borrowingMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<BorrowingResponseDTO> getAllBorrowings() {
        List<Borrowing> borrowings = borrowingRepository.findAllByOrderByDueDateDesc();
        borrowings.forEach(this::updateStatusIfNeeded);
        return borrowings.stream()
                .map(borrowingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BorrowingResponseDTO getBorrowingById(Long id) {
        Borrowing borrowing = borrowingRepository.findById(id).orElse(null);
        if (borrowing != null) {
            updateStatusIfNeeded(borrowing);
            return borrowingMapper.toResponseDTO(borrowing);
        }
        return null;
    }

    @Override
    public List<BorrowingResponseDTO> getBorrowingsOfCurrentUser() {
        Long currentUserId = getCurrentUserId();
        List<Borrowing> borrowings = borrowingRepository.findByUserIdOrderByDueDateDesc(currentUserId);
        borrowings.forEach(this::updateStatusIfNeeded);
        return borrowings.stream()
                .map(borrowingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

        @Override
    public List<BorrowingResponseDTO> getBorrowingsByUserName(String username) {
        List<Borrowing> borrowings = borrowingRepository.findByUser_UsernameOrderByDueDateDesc(username);
        borrowings.forEach(this::updateStatusIfNeeded);
        return borrowings.stream()
                .map(borrowingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BorrowingResponseDTO> getBorrowingsByBookName(String name) {
        List<Borrowing> borrowings = borrowingRepository.findByBook_NameOrderByDueDateDesc(name);
        borrowings.forEach(this::updateStatusIfNeeded);
        return borrowings.stream()
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
            updateStatusIfNeeded(borrowing);
            Borrowing updatedBorrowing = borrowingRepository.save(borrowing);
            return borrowingMapper.toResponseDTO(updatedBorrowing);
        }
        return null;
    }

    @Override
    public void deleteBorrowing(Long id) {
        borrowingRepository.deleteById(id);
    }

    public Long getCurrentUserId () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"))
                    .getId();
        }
        throw new AccessDeniedException("User not authenticated");
    }

    private void updateStatusIfNeeded(Borrowing borrowing) {
        borrowing.updateStatusBasedOnTime();
        borrowingRepository.save(borrowing);
    }
}

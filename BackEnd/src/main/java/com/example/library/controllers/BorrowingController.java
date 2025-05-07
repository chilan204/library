package com.example.library.controllers;

import com.example.library.dto.request.BorrowingRequestDTO;
import com.example.library.dto.response.BorrowingResponseDTO;
import com.example.library.services.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {
    private final BorrowingService borrowingService;

    @Autowired
    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping("/me")
    public ResponseEntity<List<BorrowingResponseDTO>> getBorrowingsOfCurrentUser() {
        return ResponseEntity.ok(borrowingService.getBorrowingsOfCurrentUser());
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BorrowingResponseDTO>> getAllBorrowings() {
        return ResponseEntity.ok(borrowingService.getAllBorrowings());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BorrowingResponseDTO> getBorrowingById(@PathVariable Long id) {
        BorrowingResponseDTO borrowing = borrowingService.getBorrowingById(id);
        return borrowing != null ? ResponseEntity.ok(borrowing) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    public ResponseEntity<List<BorrowingResponseDTO>> getBorrowingsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(borrowingService.getBorrowingsByUserId(userId));
    }

    @GetMapping("/book/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BorrowingResponseDTO>> getBorrowingsByBookId(@PathVariable Long bookId) {
        return ResponseEntity.ok(borrowingService.getBorrowingsByBookId(bookId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BorrowingResponseDTO> createBorrowing(@RequestBody BorrowingRequestDTO borrowingDTO) {
        return ResponseEntity.ok(borrowingService.createBorrowing(borrowingDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BorrowingResponseDTO> updateBorrowing(@PathVariable Long id, @RequestBody BorrowingRequestDTO borrowingDTO) {
        BorrowingResponseDTO updatedBorrowing = borrowingService.updateBorrowing(id, borrowingDTO);
        return updatedBorrowing != null ? ResponseEntity.ok(updatedBorrowing) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable Long id) {
        borrowingService.deleteBorrowing(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.library.controllers;

import com.example.library.dto.request.BorrowingRequestDTO;
import com.example.library.dto.response.BorrowingResponseDTO;
import com.example.library.services.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<BorrowingResponseDTO>> getAllBorrowings() {
        return ResponseEntity.ok(borrowingService.getAllBorrowings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingResponseDTO> getBorrowingById(@PathVariable Long id) {
        BorrowingResponseDTO borrowing = borrowingService.getBorrowingById(id);
        return borrowing != null ? ResponseEntity.ok(borrowing) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<BorrowingResponseDTO>> getBorrowingsByUserName(@PathVariable String username) {
        return ResponseEntity.ok(borrowingService.getBorrowingsByUserName(username));
    }

    @GetMapping("/book/{name}")
    public ResponseEntity<List<BorrowingResponseDTO>> getBorrowingsByBookName(@PathVariable String name) {
        return ResponseEntity.ok(borrowingService.getBorrowingsByBookName(name));
    }

    @PostMapping
    public ResponseEntity<BorrowingResponseDTO> createBorrowing(@RequestBody BorrowingRequestDTO borrowingDTO) {
        return ResponseEntity.ok(borrowingService.createBorrowing(borrowingDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowingResponseDTO> updateBorrowing(@PathVariable Long id, @RequestBody BorrowingRequestDTO borrowingDTO) {
        BorrowingResponseDTO updatedBorrowing = borrowingService.updateBorrowing(id, borrowingDTO);
        return updatedBorrowing != null ? ResponseEntity.ok(updatedBorrowing) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable Long id) {
        borrowingService.deleteBorrowing(id);
        return ResponseEntity.noContent().build();
    }
}

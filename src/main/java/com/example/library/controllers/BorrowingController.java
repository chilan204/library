package com.example.library.controllers;

import com.example.library.entities.Borrowing;
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

    @GetMapping
    public ResponseEntity<List<Borrowing>> getAllBorrowings() {
        return ResponseEntity.ok(borrowingService.getAllBorrowings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrowing> getBorrowingById(@PathVariable Long id) {
        return ResponseEntity.ok(borrowingService.getBorrowingById(id));
    }

    @GetMapping("/user/{userId}")
    public List<Borrowing> getBorrowingsByUser(@PathVariable Long userId) {
        return borrowingService.getBorrowingsByUserId(userId);
    }

    @GetMapping("/book/{bookId}")
    public List<Borrowing> getBorrowingsByBook(@PathVariable Long bookId) {
        return borrowingService.getBorrowingsByBookId(bookId);
    }

    @PostMapping
    public ResponseEntity<Borrowing> createBorrowing(@RequestBody Borrowing borrowing) {
        return ResponseEntity.ok(borrowingService.createBorrowing(borrowing));
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<Borrowing> updateBorrowing(@PathVariable Long id, @RequestBody Borrowing updatedBorrowing) {
        return ResponseEntity.ok(borrowingService.updateBorrowing(id, updatedBorrowing));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable Long id) {
        borrowingService.deleteBorrowing(id);
        return ResponseEntity.noContent().build();
    }
}

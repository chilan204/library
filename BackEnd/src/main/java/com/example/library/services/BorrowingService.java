package com.example.library.services;

import com.example.library.entities.Borrowing;

import java.util.List;

public interface BorrowingService {
    List<Borrowing> getAllBorrowings();

    Borrowing getBorrowingById(Long id);

    List<Borrowing> getBorrowingsByUserId(Long userId);

    List<Borrowing> getBorrowingsByBookId(Long bookId);

    Borrowing createBorrowing(Borrowing borrowing);

    Borrowing updateBorrowing(Long id, Borrowing updatedBorrowing);

    void deleteBorrowing(Long id);
}

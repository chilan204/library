package com.example.library.services.impl;

import com.example.library.entities.Borrowing;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.BorrowingRepository;
import com.example.library.repositories.UserRepository;
import com.example.library.services.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowingServiceImpl implements BorrowingService {
    private final BorrowingRepository borrowingRepository;

    @Autowired
    public BorrowingServiceImpl(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Borrowing> getAllBorrowings() {
        return borrowingRepository.findAll();
    }

    @Override
    public Borrowing getBorrowingById(Long id) {
        return borrowingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Borrowing> getBorrowingsByUserId(Long userId) {
        return borrowingRepository.findByUserId(userId);
    }

    @Override
    public List<Borrowing> getBorrowingsByBookId(Long bookId) {
        return borrowingRepository.findByBookId(bookId);
    }

    @Override
    public Borrowing createBorrowing(Borrowing borrowing) {
        return borrowingRepository.save(borrowing);
    }

    @Override
    public Borrowing updateBorrowing(Long id, Borrowing updatedBorrowing) {
        Borrowing borrowing = getBorrowingById(id);
        borrowing.setReturnDate(updatedBorrowing.getReturnDate());
        borrowing.setStatus(updatedBorrowing.getStatus());
        return borrowingRepository.save(borrowing);
    }

    @Override
    public void deleteBorrowing(Long id) {
        borrowingRepository.deleteById(id);
    }
}

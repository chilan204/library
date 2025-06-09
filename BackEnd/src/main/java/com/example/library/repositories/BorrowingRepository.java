package com.example.library.repositories;

import com.example.library.entities.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findAllByOrderByDueDateDesc();
    List<Borrowing> findByUserIdOrderByDueDateDesc(Long userId);
    List<Borrowing> findByUser_UsernameOrderByDueDateDesc(String username);
    List<Borrowing> findByBook_NameOrderByDueDateDesc(String name);
    List<Borrowing> findByBookIdOrderByDueDateDesc(Long bookId);
}

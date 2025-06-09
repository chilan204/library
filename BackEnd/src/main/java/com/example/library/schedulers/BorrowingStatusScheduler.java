package com.example.library.schedulers;

import com.example.library.entities.Borrowing;
import com.example.library.repositories.BorrowingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BorrowingStatusScheduler {

    private final BorrowingRepository borrowingRepository;

    @Scheduled(cron = "0 0 0 * * ?") // Chạy mỗi ngày lúc 00:00
    @Transactional
    public void updateBorrowingStatusesDaily() {
        List<Borrowing> borrowings = borrowingRepository.findAll();

        List<Borrowing> updatedBorrowings = new ArrayList<>();
        for (Borrowing borrowing : borrowings) {
            boolean changed = borrowing.updateStatusBasedOnTime(); // trả về true nếu status thay đổi
            if (changed) {
                updatedBorrowings.add(borrowing);
            }
        }

        if (!updatedBorrowings.isEmpty()) {
            borrowingRepository.saveAll(updatedBorrowings);
            System.out.println("Updated " + updatedBorrowings.size() + " borrowing statuses.");
        } else {
            System.out.println("No borrowing statuses need update.");
        }
    }
}
package com.example.library.entities;

import com.example.library.entities.base.BaseEntity;
import com.example.library.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "borrowings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Borrowing extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private LocalDateTime borrowDate;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    @Column
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}

package com.example.library.entities;

import com.example.library.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false, length = 100)
    private Integer publicationYear;

    @Column(nullable = false, unique = true, length = 100)
    private String isbn;

    @Column(nullable = false, unique = true, length = 100)
    private String image;

    @Column(nullable = false, length = 100)
    private Long categoryId;
}
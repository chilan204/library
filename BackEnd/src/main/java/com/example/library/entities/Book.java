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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(nullable = false, length = 100)
    private Integer publicationYear;

    @Column(nullable = false, unique = true, columnDefinition = "LONGTEXT")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
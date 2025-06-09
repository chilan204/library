package com.example.library.entities;

import com.example.library.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "authors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate birthDate;
}

package com.example.library.entities;

import com.example.library.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String name;

//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Book> books;
}

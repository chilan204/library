package com.example.library.mapper;

import com.example.library.dto.request.BorrowingRequestDTO;
import com.example.library.dto.response.BorrowingResponseDTO;
import com.example.library.dto.response.BookResponseDTO;
import com.example.library.dto.response.UserResponseDTO;
import com.example.library.entities.Borrowing;
import com.example.library.entities.Book;
import com.example.library.entities.User;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BorrowingMapper {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    @Autowired
    public BorrowingMapper(
            UserRepository userRepository,
            BookRepository bookRepository,
            UserMapper userMapper,
            BookMapper bookMapper
    ) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
    }

    public Borrowing toEntity(BorrowingRequestDTO dto) {
        Borrowing borrowing = new Borrowing();
        borrowing.setCreatedBy("admin"); // Mặc định created_by là admin
        
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        borrowing.setUser(user);
        
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        borrowing.setBook(book);
        
        borrowing.setBorrowDate(dto.getBorrowDate());
        borrowing.setDueDate(dto.getDueDate());
        borrowing.setReturnDate(dto.getReturnDate());
        borrowing.setStatus(dto.getStatus());
        
        return borrowing;
    }

    public BorrowingResponseDTO toResponseDTO(Borrowing entity) {
        BorrowingResponseDTO dto = new BorrowingResponseDTO();
        dto.setId(entity.getId());
        
        // Chuyển đổi User entity sang UserResponseDTO
        User user = entity.getUser();
        UserResponseDTO userDTO = userMapper.toResponseDTO(user);
        dto.setUser(userDTO);
        
        // Chuyển đổi Book entity sang BookResponseDTO
        Book book = entity.getBook();
        BookResponseDTO bookDTO = bookMapper.toResponseDTO(book);
        dto.setBook(bookDTO);
        
        dto.setBorrowDate(entity.getBorrowDate());
        dto.setReturnDate(entity.getReturnDate());
        dto.setStatus(entity.getStatus().name());
        
        return dto;
    }
} 
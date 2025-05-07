package com.example.library.dto.response;

import com.example.library.enums.Role;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String userCode;
    private Role role;
} 
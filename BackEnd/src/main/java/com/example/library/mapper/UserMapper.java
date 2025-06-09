package com.example.library.mapper;

import com.example.library.dto.request.UserRequestDTO;
import com.example.library.dto.response.UserResponseDTO;
import com.example.library.entities.User;
import com.example.library.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        user.setUserCode(dto.getUserCode());
        user.setRole(Role.USER);
        return user;
    }

    public UserResponseDTO toResponseDTO(User entity) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setUserCode(entity.getUserCode());
        return dto;
    }
} 
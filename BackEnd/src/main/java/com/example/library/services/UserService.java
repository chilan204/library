package com.example.library.services;

import com.example.library.dto.request.UserRequestDTO;
import com.example.library.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO createUser(UserRequestDTO userDTO);

    UserResponseDTO updateUser(Long id, UserRequestDTO userDTO);

    void deleteUser(Long id);
}

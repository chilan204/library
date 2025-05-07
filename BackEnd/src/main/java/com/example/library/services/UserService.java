package com.example.library.services;

import com.example.library.dto.request.UserRequestDTO;
import com.example.library.dto.response.UserResponseDTO;
import com.example.library.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO createUser(UserRequestDTO userDTO);

    UserResponseDTO updateUser(Long id, UserRequestDTO userDTO);

    void deleteUser(Long id);

    Optional<User> findByUsername(String username);
}

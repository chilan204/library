package com.example.library.services.impl;

import com.example.library.dto.request.UserRequestDTO;
import com.example.library.dto.response.UserResponseDTO;
import com.example.library.entities.User;
import com.example.library.mapper.UserMapper;
import com.example.library.repositories.UserRepository;
import com.example.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user != null ? userMapper.toResponseDTO(user) : null;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userDTO) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user.setPassword(userDTO.getPassword());
            user.setUserCode(userDTO.getUserCode());
            User updatedUser = userRepository.save(user);
            return userMapper.toResponseDTO(updatedUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
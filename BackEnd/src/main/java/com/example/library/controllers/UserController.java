package com.example.library.controllers;

import com.example.library.dto.request.UserRequestDTO;
import com.example.library.dto.response.UserResponseDTO;
import com.example.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userDTO) {
        UserResponseDTO updatedUser = userService.updateUser(id, userDTO);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

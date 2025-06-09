package com.example.library.controllers;

import com.example.library.config.security.JwtUtil;
import com.example.library.dto.request.UserRequestDTO;
import com.example.library.dto.response.UserResponseDTO;
import com.example.library.entities.User;
import com.example.library.enums.Role;
import com.example.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDTO userRequestDTO) {
        if (userService.findByUsername(userRequestDTO.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken");
        }

        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        UserResponseDTO newUser = userService.createUser(userRequestDTO);

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDTO userRequestDTO) {
        User user = userService.findByUsername(userRequestDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(userRequestDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("role", user.getRole().name());
        response.put("username", user.getUsername());

        return ResponseEntity.ok(response);
    }
}

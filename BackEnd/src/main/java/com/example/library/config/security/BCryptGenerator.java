package com.example.library.config.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptGenerator {
    public static void main(String[] args) {
        String rawPassword = "123456"; // Mật khẩu gốc
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(rawPassword);

        System.out.println("Mật khẩu đã mã hóa: " + hashedPassword);
    }
}

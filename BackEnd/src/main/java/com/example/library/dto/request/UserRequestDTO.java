package com.example.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String username;

    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;

    @NotBlank(message = "Mã người dùng không được để trống")
    private String userCode;
}
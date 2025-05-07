package com.example.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class AuthorRequestDTO {
    @NotBlank(message = "Tên thể loại không được để trống")
    @Size(max = 255, message = "Tên thể loại không được vượt quá 255 ký tự")
    private String name;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotBlank(message = "Năm sinh không được để trống")
    private Date birthDate;
} 
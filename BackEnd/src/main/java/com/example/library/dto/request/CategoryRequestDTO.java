package com.example.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    @NotBlank(message = "Tên thể loại không được để trống")
    @Size(max = 255, message = "Tên thể loại không được vượt quá 255 ký tự")
    private String name;
} 
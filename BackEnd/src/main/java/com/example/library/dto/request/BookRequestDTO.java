package com.example.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookRequestDTO {
    @NotBlank(message = "Tên sách không được để trống")
    private String name;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotBlank(message = "ID tác giả không được để trống")
    private Long authorId;

    @NotNull(message = "Năm xuất bản không được để trống")
    private Integer publicationYear;

    @NotBlank(message = "Ảnh không được để trống")
    private String image;

    @NotNull(message = "ID thể loại không được để trống")
    private Long categoryId;
} 
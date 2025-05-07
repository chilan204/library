package com.example.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookRequestDTO {
    @NotBlank(message = "Tên sách không được để trống")
    private String name;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotBlank(message = "ID tác giả không được để trống")
    @Positive(message = "ID tác giả phải là số dương")
    private Long authorId;

    @NotNull(message = "Năm xuất bản không được để trống")
    @Positive(message = "Năm xuất bản phải là số dương")
    private Integer publicationYear;

    @NotBlank(message = "Mã ISBN không được để trống")
    private String isbn;

    @NotBlank(message = "Ảnh không được để trống")
    private String image;

    @NotNull(message = "ID thể loại không được để trống")
    @Positive(message = "ID thể loại phải là số dương")
    private Long categoryId;
} 
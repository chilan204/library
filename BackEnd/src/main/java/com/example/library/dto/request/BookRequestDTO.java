package com.example.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {
    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;

    @NotBlank(message = "Tác giả không được để trống")
    private String author;

    @NotNull(message = "Năm xuất bản không được để trống")
    @Positive(message = "Năm xuất bản phải là số dương")
    private Integer publicationYear;

    @NotBlank(message = "ISBN không được để trống")
    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ])?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$", 
            message = "Định dạng ISBN không hợp lệ")
    private String isbn;

    @NotBlank(message = "URL hình ảnh không được để trống")
    private String image;

    @NotNull(message = "ID thể loại không được để trống")
    @Positive(message = "ID thể loại phải là số dương")
    private Long categoryId;
} 
package com.example.bookapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자를 자동으로 만들어줍니다. (매우 중요!)
@ToString
public class LibraryAvailabilityDto {
    private int libCode;
    private String hasBook;
    private String loanAvailable;
}

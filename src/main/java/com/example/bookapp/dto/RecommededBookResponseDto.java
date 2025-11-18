package com.example.bookapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class RecommededBookResponseDto {
    private  String regionNm;
    private List<RedDocWrapperDto> docs;
}

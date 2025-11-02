package com.example.bookapp.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class searchLibraryByLocationRequestDto {
    private String isbn13;
    private List<Integer> libraryCodeList;
}

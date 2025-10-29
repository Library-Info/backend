package com.example.bookapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class BookDto {
    @JsonProperty
    private String bookname;
    @JsonProperty
    private String authors;
    @JsonProperty
    private String publisher;
    @JsonProperty
    private String isbn13;


}

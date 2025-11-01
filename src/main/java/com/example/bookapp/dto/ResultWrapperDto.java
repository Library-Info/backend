package com.example.bookapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ResultWrapperDto {
    @JsonProperty
    private String hasBook;
    @JsonProperty
    private String loanAvailable;
}

package com.example.bookapp.dto;

import io.micrometer.observation.ObservationFilter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class DocWrapperDto {
    private BookDto doc;

}

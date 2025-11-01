package com.example.bookapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LibraryDto {
    private Long id;
    private String libName;
    private int libCode;
    private String address;
    private String tel;
    private float lattiude;
    private float longitude;
    private String homepage;
    private  String closed;
    private String operatingTime;


}

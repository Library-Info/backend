package com.example.bookapp.library;

import lombok.Getter;
import lombok.Setter;
//import org.apache.ibatis.annotations.Mapper;


@Getter
@Setter
public class Library {
    String libraryId;
    String libraryName;
    double lantitude;
    double longitude;
    String city;
    String province;
}

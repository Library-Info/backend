package com.example.bookapp.mapper;

import com.example.bookapp.dto.BookDto;
import com.example.bookapp.dto.LibraryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LibraryMapper {

    // 시,도 위도, 경도를 보고 도서관 리스트를 추출합니다.
    List<LibraryDto> findLibraryBycityandprovince( String city, String province);

}

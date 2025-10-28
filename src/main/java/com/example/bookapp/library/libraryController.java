package com.example.bookapp.library;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/library")
public class libraryController {
    private final LibraryServiceImpl libraryService;
    public libraryController(LibraryServiceImpl libraryService) {
        this.libraryService = libraryService;
    }


    /**
     * 위도,경도를 (자기위치) 바탕으로 주변 도서관데이터를 반환한다.
     * @param lantitude
     * @param longitude
     * @return 주변 도서관 데이터 리스트
     * city: ex 시흥시
     * province: ex) 경기도
     */
    @GetMapping("/list")
    public ResponseEntity<?> getlibraryList(@RequestParam float lantitude,float longitude,String city, String province){

        System.out.println("Getting library list");
        List<?> libraryList=libraryService.getLibraryBylocation(lantitude,longitude,city,province);
        return ResponseEntity.ok().build();
    }
}

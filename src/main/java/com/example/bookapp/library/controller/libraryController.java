package com.example.bookapp.library.controller;

import com.example.bookapp.library.LibraryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity<?> getlibraryList(@RequestParam double lantitude,double longitude,String city, String province){

        System.out.println("Getting library list");
        List<?> libraryList=libraryService.getLibraryBylocation(lantitude,longitude,city,province);
        return ResponseEntity.ok().body(libraryList);
    }

    /**
     * 연령대별 지역에 따른 추천도서
     * 성별코드 0: 남성, 1:여성,2:미상
     * 연령코드
     * 0:영유아(0-5세)
     * 6:유야(6-7세)
     * 8:초등(8-13세)
     * 14:청소년(14-19세)
     * 20:20대
     * 30:30대
     * 40:40대
     * 50:50대
     * 60:60대
     * -1:미상
     */
    @GetMapping("/recommend")
    public ResponseEntity<?> getRecommendBookList(@RequestParam int age,@RequestParam String gender,@RequestParam String region){
        System.out.println("Getting recommend book list");
        List<?>recommendBookList=libraryService.getRecommendBookByFilter(age,gender,region);
        return ResponseEntity.ok().body(recommendBookList);
    }


}

package com.example.bookapp.library.controller;

import com.example.bookapp.dto.*;
import com.example.bookapp.library.SearchServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchServiceImpl searchService;
    public SearchController(SearchServiceImpl searchService) {
        this.searchService = searchService;
    }


    /**
     * 들어온 키워드에 부합하는 책 리스트를 반환합니다.
     * @param keyword
     * @return
     */
    @GetMapping("/books")
    public ResponseEntity<?>  searchBookByLocation(@RequestParam String keyword, @RequestParam int pageNO) throws MalformedURLException {
        List<BookDto> searchBookList=searchService.searchBookByKeyword(keyword,pageNO);
        return ResponseEntity.ok(searchBookList);

    }

    /**
     * isbn 번호를 토대로 그 도서를 소장하고 있는 을 나열 합니다.
     * @param searchLibraryByLocationRequestDto
     * @return List<LibraryAvailabilityDto>
     *     LibraryAvailabilityDto 안에 도서관 코드, 책 소장여부, 책 대출 가능 여부 들어있음
     *     가능하면 Y, 불가능하면 N 출력됨.
     * @throws MalformedURLException
     */
    @PostMapping("/isbn/library")
    public ResponseEntity<?>  searchLibraryByLocation(@RequestBody searchLibraryByLocationRequestDto dto)  {
        List<LibraryAvailabilityDto> searchLibraryListByisbn =searchService.searchLibraryByIsbn(dto.getIsbn13(),dto.getLibraryCodeList());
        return ResponseEntity.ok(searchLibraryListByisbn);

    }

}

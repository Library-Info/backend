package com.example.bookapp.library;

import com.example.bookapp.dto.BookDto;
import com.example.bookapp.dto.DocWrapperDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.net.MalformedURLException;
import java.util.List;

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

}

package com.example.bookapp.library;

import com.example.bookapp.dto.BookDto;
import com.example.bookapp.dto.DocWrapperDto;
import com.example.bookapp.dto.LibraryResponseDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import java.awt.print.Book;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl {

    @Autowired
    private RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${my.Authkey}")
    private String authkey;

    public SearchServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    /**
     * 들어온 키워드에 부합하는 책들을 리스트로 반환하는 메서드
     *
     * @param keyword
     * @param pageNO
     * @return
     */
    public List<BookDto> searchBookByKeyword(String keyword, int pageNO) throws MalformedURLException {

        //1. UriComponentsBuilder로 안전하게 URL 생성 (자동으로 인코딩 처리)
        URI uri= UriComponentsBuilder
                .fromUriString("http://data4library.kr")
                .path("/api/srchBooks")
                .queryParam("authKey",authkey)
                .queryParam("keyword",keyword)
                .queryParam("pageNO",pageNO)
                .queryParam("pageSize",10)
                .queryParam("format","json")
                .encode()
                .build()
                .toUri();
        System.out.println(uri);
        // 2. RestTemplate을 사용하여 GET 요청 보내고 응답을 String으로 받기
        // GET 요청이므로 getForObject() 사용, POST는 postForObject() 사용
        String jsonString = restTemplate.getForObject(uri, String.class);

        //3. ObjectMapper를 사용하여 Json파싱
        try {
            //전체 Json구조에서 reponse 노드만 추출
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode responseNode = rootNode.path("response");

            //response 노드의 내용을 LibraryResponseDto로 변환
            LibraryResponseDto libraryResponseDto = objectMapper.treeToValue(responseNode, LibraryResponseDto.class);
            //3. 최종적으로 원하는 BookDto의 리스트로 변환하여 출력

            return libraryResponseDto.getDocs().stream() //.stream 으로 리스트를 스트림으로 변환
                    .map(DocWrapperDto::getDoc)
                    .collect(Collectors.toList());
        }
        catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }



        //System.out.println(jsonString);



//        try {
//            URL searchUrl = new URL("http://data4library.kr/api/srchBooks?authKey=" + authkey + "&keyword=" + keyword + "&pageNo=" + pageNO + "&pageSize=10");
//            System.out.println(searchUrl.toString());
//            HttpURLConnection connection=(HttpURLConnection) searchUrl.openConnection();
//            connection.setRequestMethod("GET");
//            int responseCode = connection.getResponseCode();
//            System.out.println(responseCode);
//            if (responseCode == 200) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                StringBuffer stringBuffer = new StringBuffer();
//                String inputLine;
//
//                while ((inputLine = br.readLine()) != null) {
//                    stringBuffer.append(inputLine);
//                }
//                br.close();
//                String jsonString = stringBuffer.toString();
//                System.out.println(jsonString);
//
//            }
//        }catch (MalformedURLException e){
//            e.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        List<Book> searchBookList=null;
        //return null;
    }
}

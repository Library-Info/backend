package com.example.bookapp.library;

import com.example.bookapp.dto.*;
import com.example.bookapp.library.controller.GeoDistanceCalculator;
import com.example.bookapp.mapper.LibraryMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl {

    @Autowired
    private RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${my.SearchRadiusMeter}")
    private  double  SEARCH_RADIUS_METER;

    //@Value("${my.authkey")
    private String authkey="6d0e94c11f407ef53554afd3bef2eb1ad3460e28f505c26d241d712257fcb15a";

    private final LibraryMapper libraryMapper;
    public LibraryServiceImpl(ObjectMapper objectMapper, LibraryMapper libraryMapper ) {
        this.objectMapper = objectMapper;
        this.libraryMapper = libraryMapper;

    }

    /**
     *  내위치의 위도 경도를 받으면, 시도 행정구역을 반환하는 메소드
     *  시도를 반환하는 그 시도헤 해당하는 리스트를 추출함
     *  그걸 리스트를 돌려가면서 거리를 계산함. 500m 안인 도서관 데이터를 추출함.
     * @param mylantitude
     * @param mylongitude
     * @param city
     * @param province
     */
    public List<?> getLibraryBylocation(double mylantitude, double mylongitude, String city, String province) {
        // 해당 시도의 도서관 리스트
        List<LibraryDto> libraryList =libraryMapper.findLibraryBycityandprovince(city,province);
        System.out.println("Getting library list"+libraryList);
        List<LibraryDto> libraryListIn500 = new ArrayList<>();
        // 리스트를 돌려가면서 500m 안인지 계산하는  공식 메서드
        for(LibraryDto library:libraryList){
            boolean isWithinRadius= GeoDistanceCalculator.isWithinRadius(mylantitude,mylongitude,library.getLatitude(),library.getLongitude(),SEARCH_RADIUS_METER);
            if(isWithinRadius){
                libraryListIn500.add(library);
            }
        }
        System.out.println("libraryListIn500"+libraryListIn500);

        if(libraryListIn500.isEmpty()){
            return libraryList;
        }

        return libraryListIn500;
    }


    public List<?> getRecommendBookByFilter(int age, String gender,String region) {
        int ageCode=libraryMapper.findAgeCode(age);
        int genderCode= libraryMapper.findGenderCode(gender);
        int regionCode=libraryMapper.findRegionCode(region);
        System.out.println("getRecommendBookByFilter"+ageCode+genderCode+regionCode);

        URI uri;
        uri= UriComponentsBuilder
                .fromUriString("http://data4library.kr")
                .path("/api/loanItemSrchByLib")
                .queryParam("authKey",authkey)
                .queryParam("region",regionCode)
                .queryParam("gender",genderCode)
                .queryParam("age",ageCode)
                .queryParam("pageNO",1)
                .queryParam("pageSize",5)
                .queryParam("format","json")

                .encode()
                .build()
                .toUri();
        try{
            String jsonString = restTemplate.getForObject(uri, String.class);
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode responseNode = rootNode.path("response");
            System.out.println("responseNode"+responseNode);
            RecommededBookResponseDto libraryResponseDto = objectMapper.treeToValue(responseNode, RecommededBookResponseDto.class);
            System.out.println("libraryResponseDto"+libraryResponseDto);
            return libraryResponseDto.getDocs().stream() //.stream 으로 리스트를 스트림으로 변환
                    .map(RedDocWrapperDto::getDoc)
                    .collect(Collectors.toList());


        }catch (Exception e){
            e.printStackTrace();
        }
        return Collections.emptyList();


    }
}

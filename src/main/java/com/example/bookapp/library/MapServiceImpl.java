package com.example.bookapp.library;

import com.example.bookapp.dto.LibraryDto;
import com.example.bookapp.dto.RecommededBookResponseDto;
import com.example.bookapp.dto.RedDocWrapperDto;
import com.example.bookapp.mapper.LibraryMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapServiceImpl {

    LibraryMapper libraryMapper;
    // 아무것도 안들어오면 기본값으로 도보 경로 추천

    public MapServiceImpl(LibraryMapper libraryMapper) {
        this.libraryMapper = libraryMapper;
    }

    //아니면 경로에 맞춰서 안내
    // 이동수단 종류
    //	https://map.kakao.com/link/from/에이치스퀘어,37.402056,127.108212/to/카카오판교아지트,37.3952969470752,127.110449292622
    public void navigateToMap(String method){


    }

    /**
     * 도로정보를 반영하여 도서관 리스트를 반환합니다.
     * 지정한 경로안에 있는, 그중 가장 최단시간으로 갈수 있는 도서관정보를 반환합니다.
     */
    public List<?> getLibraryListByKaKaoMap(String city,String province,double myY,double myX,int radius) throws IOException {


        // 중요: 이 부분에 발급받은 REST API 키를 입력하세요.
        String REST_API_KEY = "e140120f18ebca253dc9456e7b42857e";
        List<LibraryDto> libraryList =libraryMapper.findLibraryBycityandprovince(city,province);

        try {
            // 1. DTO 객체 생성 (데이터를 객체로 관리) 원래위치 데이터로 가져오고
            Point origin = new Point(myX, myY);
            //리스트를 돌면서 도서관 집어넣기
            List<Destination> destinations = new ArrayList<>();
            for (int i=0 ; i<libraryList.toArray().length;i++) {
                LibraryDto libraryDto = libraryList.get(i);
                Destination destination=new Destination((double) libraryDto.getLongitude(),(double) libraryDto.getLatitude(),String.valueOf(libraryDto.getId()));
                destinations.add(destination);
            }

            KakaoNaviRequest requestDto = new KakaoNaviRequest(origin, destinations, radius);

            // 2. ObjectMapper를 사용하여 DTO 객체를 JSON 문자열로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonPayload = objectMapper.writeValueAsString(requestDto);

            System.out.println("생성된 JSON Payload: " + jsonPayload); // 생성된 JSON 확인

            // 3. HttpClient를 사용한 요청 (이전과 동일)
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://apis-navi.kakaomobility.com/v1/destinations/directions"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "KakaoAK " + REST_API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload)) // 변환된 JSON 문자열 사용
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("응답 코드 : " + response.statusCode());
            System.out.println("응답 내용 : " + response.body());

            // --- 3. 응답 JSON을 DTO 객체로 변환 ---
            System.out.println("--- Raw Response ---");
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            System.out.println("---------------------\n");

            if (response.statusCode() == 200) {
                KakaoNaviResponse naviResponse = objectMapper.readValue(response.body(), KakaoNaviResponse.class);

                // --- 4. 변환된 DTO 객체의 데이터 활용 ---
                System.out.println("--- Parsed Response Data ---");
                System.out.println("Transaction ID: " + naviResponse.getTransId());

                List<LibraryDto> roureInfoList=new ArrayList<>();
                for (RouteInfo route : naviResponse.getRoutes()) {
                    System.out.println("\n>> Destination Key: " + route.getKey());
                    if (route.getResultCode() == 0) {
                        System.out.println("   Result: " + route.getResultMsg());
                        System.out.println("   Distance: " + route.getSummary().getDistance() + " meters");
                        System.out.println("   Duration: " + route.getSummary().getDuration() + " seconds");
                        for (LibraryDto dto:libraryList){
                            if(dto.getLibCode()==Integer.parseInt(route.getKey())){
                                LibraryDto libraryDto=libraryList.get(Integer.parseInt(route.getKey()));
                                roureInfoList.add(libraryDto);
                            }
                        }


                    } else {
                        System.out.println("   Error: " + route.getResultMsg());
                    }
                }
                System.out.println("--------------------------");
                return roureInfoList;

            }




        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}

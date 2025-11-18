package com.example.bookapp.library;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// API 응답 전체를 감싸는 메인 클래스
// 알 수 없는 속성이 JSON에 있어도 에러를 발생시키지 않도록 설정
@JsonIgnoreProperties(ignoreUnknown = true)//. API 응답에 DTO에 정의되지 않은 새로운 필드가 추가되더라도, 프로그램이 오류 없이 정상적으로 동작하도록 만들어 줍니다
@Getter
@Setter
public class KakaoNaviResponse {
    @JsonProperty("trans_id")
    private String transId;

    private List<RouteInfo> routes;

    @Override
    public String toString() {
        return "KakaoNaviResponse{" +
                "transId='" + transId + '\'' +
                ", routes=" + routes +
                '}';
    }
}

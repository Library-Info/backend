package com.example.bookapp.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// routes 배열의 각 경로 정보를 위한 클래스
@Getter
@Setter
@NoArgsConstructor
public class RouteInfo {
    @JsonProperty("result_code") // JSON의 result_code 필드를 resultCode 필드에 매핑
    private int resultCode;

    @JsonProperty("result_msg")
    private String resultMsg;

    private String key;
    private RouteSummary summary;


    @Override
    public String toString() {
        return "RouteInfo{" +
                "resultCode=" + resultCode +
                ", resultMsg='" + resultMsg + '\'' +
                ", key='" + key + '\'' +
                ", summary=" + summary +
                '}';
    }
}

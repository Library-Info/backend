package com.example.bookapp.library;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
// API 요청 전체를 감싸는 클래스
public class KakaoNaviRequest {
    private Point origin;
    private List<Destination> destinations;
    private int radius;


}

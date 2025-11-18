package com.example.bookapp.library;

import lombok.Getter;
import lombok.Setter;

// summary 객체를 위한 클래스
@Getter
@Setter
public class RouteSummary {
    private int distance; // 거리 (미터)
    private int duration; // 시간 (초)


    @Override
    public String toString() {
        return "RouteSummary{" +
                "distance=" + distance +
                ", duration=" + duration +
                '}';
    }
}

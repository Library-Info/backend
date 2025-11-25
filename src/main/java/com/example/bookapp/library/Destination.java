package com.example.bookapp.library;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// key 필드가 추가된 목적지 클래스
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Destination extends Point {
    private String key;

    // 생성자, Getter, Setter
    public Destination(Double x, Double y, String key) {
        super(x, y);
        this.key = key;
    }


}


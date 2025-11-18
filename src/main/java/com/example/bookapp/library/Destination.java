package com.example.bookapp.library;

// key 필드가 추가된 목적지 클래스
public class Destination extends Point {
    private String key;

    // 생성자, Getter, Setter
    public Destination(Double x, Double y, String key) {
        super(x, y);
        this.key = key;
    }


}


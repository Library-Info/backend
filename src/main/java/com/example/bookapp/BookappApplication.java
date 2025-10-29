package com.example.bookapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BookappApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookappApplication.class, args);
    }

    @Bean //이 메소드가 반환하는 객체를 스프링이 관리하는 Bean으로 등록
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

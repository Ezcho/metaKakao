package com.project.metakakao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.project.metakakao"}) //테이블 자동 생성이 안돼서 넣음
public class MetaKakaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetaKakaoApplication.class, args);
    }
}
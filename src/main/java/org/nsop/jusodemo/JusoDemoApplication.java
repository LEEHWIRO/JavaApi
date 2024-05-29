package org.nsop.jusodemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.nsop.jusodemo.service.impl") // 매퍼 인터페이스가 위치한 패키지
public class JusoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JusoDemoApplication.class, args);
    }

}

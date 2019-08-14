package com.example.musjoy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.musjoy.mapper")
public class MusjoyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusjoyApplication.class, args);
    }

}

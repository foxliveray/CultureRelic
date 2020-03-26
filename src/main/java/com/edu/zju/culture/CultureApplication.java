package com.edu.zju.culture;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.edu.zju.culture.mbg.mapper")
public class CultureApplication {

    public static void main(String[] args) {
        SpringApplication.run(CultureApplication.class, args);
    }

}
package com.test.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.test.security.mapper")
@SpringBootApplication
public class CustomUserServiceSecurityBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomUserServiceSecurityBootApplication.class, args);
    }
}

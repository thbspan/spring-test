package com.test.web;

import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebfluxApplication {

    public static void main(String[] args) {
        System.out.println("test");
        SpringApplication.run(SpringWebfluxApplication.class, args);
    }
}

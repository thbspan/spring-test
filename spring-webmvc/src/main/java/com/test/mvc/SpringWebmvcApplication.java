package com.test.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringWebmvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringWebmvcApplication.class, args);
    }
}

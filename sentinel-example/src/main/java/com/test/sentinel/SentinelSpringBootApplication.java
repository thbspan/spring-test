package com.test.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SentinelSpringBootApplication {

    public static void main(String[] args) {
        // 设置系统属性 project.name，提供给 sentinel 读取
        System.setProperty("project.name", "sentinel-example");
        SpringApplication.run(SentinelSpringBootApplication.class, args);
    }
}

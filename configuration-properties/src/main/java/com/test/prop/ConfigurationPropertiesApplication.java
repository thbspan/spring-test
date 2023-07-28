package com.test.prop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
// 用于描查找并注册@ConfigurationProperties类，参考 https://blog.csdn.net/skh2015java/article/details/120141409
@ConfigurationPropertiesScan
public class ConfigurationPropertiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationPropertiesApplication.class, args);
    }
}

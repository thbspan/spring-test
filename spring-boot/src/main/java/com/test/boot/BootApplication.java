package com.test.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

// 3====
//@EnableConfigurationProperties
//@ComponentScan
// 2====
@EnableAutoConfiguration
@ComponentScan
// 1====
//@SpringBootApplication
//@EnableAsync
public class BootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BootApplication.class, args);
        System.out.println(context.getBean(ServerBean.class));
//        Runnable userRunnable = context.getBean(Runnable.class);
//        System.out.println(userRunnable);
//        userRunnable.run();
        System.out.println("end");
    }
}

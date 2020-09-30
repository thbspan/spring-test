package com.test.grpc.example.user;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceProviderApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(UserServiceProviderApplication.class, args);
        new CountDownLatch(1).await();
    }
}

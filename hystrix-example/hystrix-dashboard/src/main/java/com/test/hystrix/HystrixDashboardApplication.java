package com.test.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 开启hystrix dashboard功能
 * 不兼容Spring Boot 2.4.x版本，参考分支 netflix-hystrix
 */
@EnableHystrixDashboard
// 开启 Turbine 功能
@EnableTurbine
@SpringBootApplication
public class HystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }
}

package com.test;

import java.sql.SQLException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import com.test.service.ExampleService;

@MapperScan(basePackages = "com.test.mapper")
@SpringBootApplication(exclude = JtaAutoConfiguration.class)
public class ShardingJdbcApplication {

    public static void main(String[] args) throws SQLException {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(ShardingJdbcApplication.class, args)) {
            ExampleExecuteTemplate.run(applicationContext.getBean(ExampleService.class));
        }
    }
}

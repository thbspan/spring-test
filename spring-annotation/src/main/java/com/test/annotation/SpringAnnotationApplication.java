package com.test.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.test.annotation.config.TestImport;
import com.test.annotation.service.WorkerService;

@Import(TestImport.class)
@Configuration
@ComponentScan("com.test.annotation")
public class SpringAnnotationApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringAnnotationApplication.class);
        WorkerService workerService = applicationContext.getBean(WorkerService.class);
        workerService.work();
    }

    @Bean
    public T1 t1() {
        return new T1();
    }

    @Bean
    public String jack() {
        return "x";
    }

    static class T1 {

    }

    static class T2 {

    }
}
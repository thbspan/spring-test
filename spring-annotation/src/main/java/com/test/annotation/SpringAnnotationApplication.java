package com.test.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.test.annotation.service.BusinessService;

@Configuration
@ComponentScan("com.test.annotation")
public class SpringAnnotationApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringAnnotationApplication.class);
        BusinessService businessService = applicationContext.getBean(BusinessService.class);
        System.out.println(businessService.work(3));
    }
}

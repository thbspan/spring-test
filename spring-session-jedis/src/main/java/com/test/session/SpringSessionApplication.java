package com.test.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.DefaultCookieSerializerCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(proxyBeanMethods = false)
public class SpringSessionApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSessionApplication.class, args);
    }

    @Bean
    DefaultCookieSerializerCustomizer cookieSerializerCustomizer() {
        return cookieSerializer -> cookieSerializer.setCookieName("ss");
    }


}

package com.test.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.test.mvc.interceptor.DemoInterceptor;

@Configuration
public class SpringWebmvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置拦截器
        InterceptorRegistration registration = registry.addInterceptor(new DemoInterceptor());
        // 所有路径都被拦截
        registration.addPathPatterns("/**");
    }

}

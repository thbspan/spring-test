package com.test.mvc;

import com.test.mvc.converter.yaml.MappingJackson2YamlHttpMessageConverter;
import com.test.mvc.interceptor.DemoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SpringWebmvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置拦截器
        InterceptorRegistration registration = registry.addInterceptor(new DemoInterceptor());
        // 所有路径都被拦截
        registration.addPathPatterns("/**");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 添加自定义的 httpMessageConverter
        converters.add(new MappingJackson2YamlHttpMessageConverter());
    }
}

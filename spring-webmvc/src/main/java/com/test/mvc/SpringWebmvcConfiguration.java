package com.test.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.mvc.argument.converter.CsvHttpMessageConverter;
import com.test.mvc.argument.resolver.ArgDemoResolver;
import com.test.mvc.converter.yaml.MappingJackson2YamlHttpMessageConverter;
import com.test.mvc.interceptor.DemoInterceptor;
import com.test.mvc.view.resolver.EasyExcelViewResolver;
import com.test.mvc.web.filter.ResponseStatusLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import java.util.List;
import java.util.Locale;

@Configuration
public class SpringWebmvcConfiguration implements WebMvcConfigurer {

    @Bean
    public ResponseStatusLoggingInterceptor responseStatusLoggingInterceptor() {
        return new ResponseStatusLoggingInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置拦截器
        InterceptorRegistration registration = registry.addInterceptor(new DemoInterceptor());
        // 所有路径都被拦截
        registration.addPathPatterns("/**");
        // 也可以不配置拦截路径，默认拦截所有的请求
        registry.addInterceptor(responseStatusLoggingInterceptor());
    }

    @Bean
    public ViewResolver jsonViewResolver(ObjectMapper objectMapper) {
        return new JsonViewResolver(objectMapper);
    }

    @Bean
    public ViewResolver xmlViewResolver() {
        return new XmlViewResolver();
    }

    @Bean
    public EasyExcelViewResolver excelView() {
        return new EasyExcelViewResolver();
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 添加自定义的 httpMessageConverter
        converters.add(new MappingJackson2YamlHttpMessageConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType("xlsx", new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        configurer.mediaType("xls", new MediaType("application", "vnd.ms-excel"));
        configurer.favorPathExtension(true);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ArgDemoResolver());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new CsvHttpMessageConverter());
    }

    public static class JsonViewResolver implements ViewResolver {

        private final ObjectMapper objectMapper;

        public JsonViewResolver(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return new MappingJackson2JsonView(objectMapper);
        }
    }

    public static class XmlViewResolver implements ViewResolver {
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return new MappingJackson2XmlView();
        }
    }
}

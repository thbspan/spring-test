package com.test.hystrix.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.stereotype.Component;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

@Component
@WebFilter(urlPatterns = "/")
public class HystrixRequestContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 初始化 HystrixRequestContext
        // 继续过滤器
        try (HystrixRequestContext context = HystrixRequestContext.initializeContext()) {
            chain.doFilter(request, response);
        }
    }

}

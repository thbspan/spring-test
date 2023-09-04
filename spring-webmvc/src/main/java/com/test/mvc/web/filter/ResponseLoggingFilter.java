package com.test.mvc.web.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter("/*")
public class ResponseLoggingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Wrap the original response
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);

        // Pass the wrapped response through the chain
        chain.doFilter(request, responseWrapper);

        // Log response status code and headers
        int statusCode = responseWrapper.getStatus();
        LOGGER.info("Response Status Code: {}", statusCode);
        responseWrapper.getHeaderNames().forEach(headerName -> {
            LOGGER.info("Response header name={}, value={}", headerName, responseWrapper.getHeader(headerName));
        });

        // Log response body
        String responseBody = responseWrapper.getCapturedResponseBody();
        LOGGER.info("Response Body: {}", responseBody);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code
    }
}

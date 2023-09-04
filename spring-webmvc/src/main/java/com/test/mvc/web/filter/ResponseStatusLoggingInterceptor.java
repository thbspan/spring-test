package com.test.mvc.web.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class ResponseStatusLoggingInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseStatusLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // This method is called before the actual request is handled
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // This method is called after the request is completed

        // Log the response body
        String responseStatus = "Response code: " + response.getStatus();
        // Log responseBody to your desired logging framework (e.g., log4j, slf4j, etc.)
        LOGGER.info(responseStatus);
    }

}

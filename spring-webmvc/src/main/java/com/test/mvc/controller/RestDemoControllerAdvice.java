package com.test.mvc.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.test.mvc.controller")
public class RestDemoControllerAdvice {

    // NoHandlerFoundException
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RuntimeException.class)
    public Object exception(Throwable throwable) {
        return Collections.singletonMap("msg", throwable.getMessage());
    }
}

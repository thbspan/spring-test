package com.test.sentinel.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.csp.sentinel.slots.block.BlockException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BlockException.class)
    public String blockExceptionHandler(BlockException blockException, HttpServletRequest request) {
        return "请求过于频繁" + request.getRemoteHost();
    }
}

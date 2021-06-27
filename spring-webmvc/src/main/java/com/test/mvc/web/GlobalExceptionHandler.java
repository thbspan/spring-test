package com.test.mvc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.mvc.vo.CommonResult;

@ControllerAdvice(basePackages = "com.test.mvc.controller")
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult<String> exceptionHandler(Exception ex) {
        logger.error("[exceptionHandler]", ex);
        // 包装 CommonResult 结果
        return CommonResult.error(500, ex.getMessage());
    }
}

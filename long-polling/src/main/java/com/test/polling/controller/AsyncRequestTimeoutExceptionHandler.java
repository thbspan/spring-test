package com.test.polling.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import com.test.polling.model.DeferredResultResponse;

@ControllerAdvice
public class AsyncRequestTimeoutExceptionHandler {

    @ResponseBody
    @ExceptionHandler(AsyncRequestTimeoutException.class) //捕获特定异常
    public DeferredResultResponse handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        DeferredResultResponse deferredResultResponse = new DeferredResultResponse();
        DeferredResultResponse.Msg timeout = DeferredResultResponse.Msg.TIMEOUT;
        deferredResultResponse.setCode(timeout.getCode());
        deferredResultResponse.setMsg(timeout.getDesc());
        return deferredResultResponse;
    }

}

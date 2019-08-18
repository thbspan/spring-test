package com.test.polling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.test.polling.model.DeferredResultResponse;
import com.test.polling.service.DeferredResultService;

@RestController
@RequestMapping("/deferred")
public class DeferredResultController {

    private static final long DEFAULT_TIME_OUT = 2 * 60 * 1000;

    @Autowired
    private DeferredResultService deferredResultService;

    @GetMapping("/get")
    public DeferredResult get(String id) {
        DeferredResult<DeferredResultResponse> deferredResult = new DeferredResult<>(DEFAULT_TIME_OUT);
        deferredResultService.process(id, deferredResult);
        return deferredResult;
    }

    @GetMapping("/result")
    @ResponseStatus(code = HttpStatus.OK)
    public void settingResult(String id, String msg) {
        DeferredResultResponse deferredResultResponse = new DeferredResultResponse();
        DeferredResultResponse.Msg success = DeferredResultResponse.Msg.SUCCESS;
        if (success.getDesc().equals(msg)) {
            deferredResultResponse.setCode(success.getCode());
            deferredResultResponse.setMsg(success.getDesc());
        } else {
            DeferredResultResponse.Msg failed = DeferredResultResponse.Msg.FAILED;
            deferredResultResponse.setCode(failed.getCode());
            deferredResultResponse.setMsg(failed.getDesc());
        }
        deferredResultService.settingResult(id, deferredResultResponse);
    }
}

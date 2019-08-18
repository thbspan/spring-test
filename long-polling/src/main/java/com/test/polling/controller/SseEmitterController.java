package com.test.polling.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
public class SseEmitterController {


    /**
     * timeout == 0 表示永不超时（注意：浏览器端也会主动断开连接，重新请求可以连上）
     * timeout ==null(默认)，超时时间依赖底层容器
     *
     */
    private SseEmitter sseEmitter = new SseEmitter(0L);

    @RequestMapping("/test")
    public SseEmitter test() {
        return sseEmitter;
    }

    @RequestMapping("/set")
    public String set() {
        try {
            sseEmitter.send(System.currentTimeMillis());
        } catch (IOException e) {
            // ignore exception
            return "error";
        }
        return "success";
    }

    @RequestMapping("/over")
    public String over() {
        sseEmitter.complete();
        return "over";
    }
}

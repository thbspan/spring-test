package com.test.rsocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Mono;

@Controller
public class GreetingsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingsController.class);

    @MessageMapping("request-response")
    Mono<String> reqResponse(@Payload String payload) {
        LOGGER.info("收到 RR 请求信息: {}", payload);
        return Mono.just("Hello, " + payload);
    }
}

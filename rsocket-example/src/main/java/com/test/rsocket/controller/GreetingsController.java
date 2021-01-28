package com.test.rsocket.controller;

import java.time.Duration;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class GreetingsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingsController.class);

    /**
     * RR请求模型
     */
    @MessageMapping("request-response")
    Mono<String> reqResponse(@Payload String payload) {
        LOGGER.info("收到 RR 请求信息: {}", payload);
        return Mono.just("Hello, " + payload);
    }

    /**
     * FNF请求模型
     */
    @MessageMapping("fire-forget")
    Mono<Void> fnf(@Payload String payload) {
        LOGGER.info("收到 FAF 请求信息: {}", payload);
        return Mono.empty();
    }

    /**
     * 单向Stream
     */
    @MessageMapping("stream")
    Flux<String> stream(@Payload String payload) {
        return Flux.interval(Duration.ofSeconds(1)).map(aLong -> payload + LocalDateTime.now());
    }

    /**
     * 双向 Channel
     */
    @MessageMapping("channel")
    Flux<String> channel(Flux<String> settings) {
        return settings.map(s -> "你好 " + s + LocalDateTime.now());
    }
}

package com.test.rsocket.controller;

import java.time.Duration;

import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.rsocket.vo.Message;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class RSocketClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(RSocketClient.class);

    private final RSocketRequester rSocketRequester;

    public RSocketClient(RSocketRequester.Builder rSocketRequesterBuilder, RSocketStrategies strategies) {
        this.rSocketRequester = rSocketRequesterBuilder
                .rsocketStrategies(strategies)
                .tcp("localhost", 8888);
        rSocketRequester.rsocketClient().source()
                .doOnError(e -> LOGGER.error("发生错误", e))
                .doFinally(__ -> LOGGER.info("连接关闭"))
                .subscribe();
    }

    @PreDestroy
    void shutdown() {
        rSocketRequester.rsocketClient().dispose();
    }

    @GetMapping("request-response2")
    public Message requestResponse() {
        return this.rSocketRequester
                .route("request-response2")
                .data(new Message("客户端", "服务器"))
                .retrieveMono(Message.class)
                .block();
    }

    @GetMapping("fire-and-forget")
    public String fireAndForget() {
        this.rSocketRequester
                .route("fire-and-forget")
                .data(new Message("客户端", "服务器"))
                .send()
                .block();
        return "fire and forget";
    }

    @GetMapping("stream")
    public String stream() {
        this.rSocketRequester
                .route("stream-message")
                .data(new Message("客户端", "服务器"))
                .retrieveFlux(Message.class)
                .subscribe(message -> LOGGER.info("客户端stream收到响应 {}", message));
        return "stream";
    }

    @GetMapping("channel")
    public String channel() {
        Mono<Duration> setting1 = Mono.just(Duration.ofSeconds(1));
        Mono<Duration> setting2 = Mono.just(Duration.ofSeconds(3)).delayElement(Duration.ofSeconds(5));
        Mono<Duration> setting3 = Mono.just(Duration.ofSeconds(5)).delayElement(Duration.ofSeconds(15));
        Flux<Duration> settings = Flux.concat(setting1, setting2, setting3)
                .doOnNext(d -> LOGGER.info("客户端channel发送消息 {}", d.getSeconds()));
        this.rSocketRequester
                .route("channel")
                .data(settings)
                .retrieveFlux(String.class)
                .subscribe(message -> LOGGER.info("客户端channel收到响应 {}", message));
        return "channel";
    }


}

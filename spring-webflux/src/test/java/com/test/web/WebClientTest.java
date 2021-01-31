package com.test.web;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.test.web.model.Event;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebClientTest {

    private WebClient webClient;

    @BeforeEach
    public void beforeEach() {
        webClient = WebClient.create("http://localhost:8080");
    }

    @Test
    public void test() {
        Mono<String> bodyMono = webClient.get()
                .uri("/hello")
                .retrieve() // 异步的获取response消息
                .bodyToMono(String.class); // 将response解析为字符串
        System.out.println(bodyMono.block());
    }

    @Test
    public void testServerPush() {
        webClient.get()
                .uri("/times")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve() //异步的方式获取消息
                .bodyToFlux(String.class)
                .log() // 通过日志打印元素信息
                .take(10) // /times是一个无限流，这里取前10个，会导致流被取消
                .blockLast();

    }

    @Test
    public void testPostEvent() {
        Flux<Event> eventFlux = Flux.interval(Duration.ofSeconds(1))
                .map(l -> new Event(System.currentTimeMillis(), "event-" + l))
                .take(10); // 限制个数

        webClient.post()
                .uri("/events")
                .contentType(MediaType.APPLICATION_STREAM_JSON) // 2
                .body(eventFlux, Event.class) // 3
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}

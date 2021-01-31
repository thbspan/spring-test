package com.test.web.dao;

import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.test.web.model.Event;

import reactor.core.publisher.Flux;

public interface EventRepository extends ReactiveCrudRepository<Event, String> {

    /**
     * {@code @Tailable}注解的作用类似tail命令，被注解的方法将发送无线流
     */
    @Tailable
    Flux<Event> findAllBy();
}

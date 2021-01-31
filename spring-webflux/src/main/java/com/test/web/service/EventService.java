package com.test.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.test.web.dao.EventRepository;
import com.test.web.model.Event;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Mono<Void> insert(@RequestBody Flux<Event> events) {
        return eventRepository.saveAll(events).then();
    }

    public Flux<Event> listAll() {
        return eventRepository.findAll();
    }

    public Flux<Event> tail() {
        return eventRepository.findAllBy();
    }
}

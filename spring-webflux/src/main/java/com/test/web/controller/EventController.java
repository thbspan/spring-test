package com.test.web.controller;

import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.web.model.Event;
import com.test.web.service.EventService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/events")
public class EventController {
    @Resource
    private EventService eventService;

    @PostMapping
    public Mono<Void> loadEvents(@RequestBody Flux<Event> events) {
        return eventService.insert(events);
    }

    @GetMapping(produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Event> getEvents() {
        return eventService.listAll();
    }
}

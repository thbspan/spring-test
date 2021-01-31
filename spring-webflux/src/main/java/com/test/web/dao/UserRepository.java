package com.test.web.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.test.web.model.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Mono<User> findByUsername(String username);

    Mono<Long> deleteByUsername(String username);
}

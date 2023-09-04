package com.test.web.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import com.test.web.dao.UserRepository;
import com.test.web.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    public Mono<User> saveOrUpdate(User user) {
        return userRepository.save(user)
                .onErrorResume(e -> userRepository.findByUsername(user.getUsername())
                        .flatMap(oldUser -> {
                            user.setId(oldUser.getId());
                            return userRepository.save(user);
                        }));
    }

    public Mono<Long> deleteByUsername(String username) {
        return userRepository.deleteByUsername(username);
    }

    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }
}

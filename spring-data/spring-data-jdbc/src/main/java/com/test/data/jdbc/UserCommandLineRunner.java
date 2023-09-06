package com.test.data.jdbc;

import com.test.data.jdbc.dao.UserRepository;
import com.test.data.jdbc.entity.User;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

    @Resource
    private UserRepository userRepository;

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setFirstName("alva");
        user.setLastName("smith");
        // spring data 自动发送事件
        userRepository.save(user);

        User newUser = new User();
        newUser.setId(1L);
        // spring data 自动发送事件
        userRepository.delete(newUser);

        // 手动发送事件
        user.setId(100);
        applicationEventPublisher.publishEvent(user);
    }
}

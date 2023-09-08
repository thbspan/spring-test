package com.test.data.redis;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExampleCommandLineRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleCommandLineRunner.class);

    @Autowired
    private RedisTemplate<String, String> template;

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOps;

    @Override
    public void run(String... args) throws Exception {
        listOps.leftPush("editor", "ListOperations");
        LOGGER.info("redis list right pop {}", listOps.rightPop("editor"));
    }
}

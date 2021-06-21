package com.test.hystrix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;

@RestController
@RequestMapping("/demo")
public class DemoController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @CacheResult(cacheKeyMethod = "genGetUserCacheKey")
    @HystrixCommand(fallbackMethod = "getUserFallback")
    @GetMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUser(int id) {
        logger.info("request spring-webmvc service. id={}", id);
        return restTemplate.getForEntity("http://127.0.0.1:8080/users/" + id, String.class).getBody();
    }

    public String genGetUserCacheKey(int id) {
        logger.info("gen cache key. id={}", id);
        return "USER_" + id;
    }

    public String getUserFallback(int id, Throwable throwable) {
        logger.info("request fallback service. id={}", id, throwable);
        return "mock:User:" + id;
    }
}

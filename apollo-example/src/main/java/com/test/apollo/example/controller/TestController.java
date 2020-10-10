package com.test.apollo.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.apollo.example.TestProperties;

@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    @Value("${test.test}")
    private String test;

    @GetMapping
    public String test() {
        return test;
    }

    @Autowired
    private TestProperties testProperties;

    @GetMapping("/properties")
    public TestProperties testProperties() {
        return testProperties;
    }

    @GetMapping("/logger")
    public void logger() {
        LOGGER.info("[logger][test]");
    }
}

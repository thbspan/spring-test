package com.test.apollo.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "test")
public class TestProperties {

    private String test;

    public String getTest() {
        return test;
    }

    public TestProperties setTest(String test) {
        this.test = test;
        return this;
    }

}
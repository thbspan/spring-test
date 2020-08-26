package com.test.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestDemoController {

    @GetMapping(value = "/")
    public Object index() {
        Map<String, Object> params = new HashMap<>();
        params.put("key", "hello");
        System.out.println(params);

//        throw new RuntimeException("exception test");
        return params;
    }
}

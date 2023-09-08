package com.test.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, path = "/hello")
    public String hello() {
        return "hello";
    }
}

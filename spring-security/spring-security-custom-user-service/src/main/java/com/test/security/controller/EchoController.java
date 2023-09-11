package com.test.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, path = "/echo")
    public String echo() {
        return "echo";
    }

    @GetMapping("/admin/echo")
    public String admin() {
        return "admin echo";
    }

    @GetMapping("/user/echo")
    public String user() {
        return "user echo";
    }
}

package com.test.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/hello")
@Controller
public class HelloViewController {

    @GetMapping
    public String testModelAttribute() {
        return "helloView";
    }
}

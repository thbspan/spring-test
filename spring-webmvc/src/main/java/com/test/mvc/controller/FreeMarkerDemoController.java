package com.test.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ftl")
public class FreeMarkerDemoController {

    @GetMapping("info")
    public String info() {
        return "info";
    }
}

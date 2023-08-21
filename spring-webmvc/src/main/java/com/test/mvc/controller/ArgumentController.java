package com.test.mvc.controller;

import com.test.mvc.argument.resolver.ArgDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/param")
@RestController
public class ArgumentController {

    @GetMapping("demo")
    public Object demo(ArgDemo argDemo) {
        Map<String, Object> params = new HashMap<>();
        params.put("arg", argDemo);
        return params;
    }
}

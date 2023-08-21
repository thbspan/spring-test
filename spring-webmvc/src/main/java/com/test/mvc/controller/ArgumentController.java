package com.test.mvc.controller;

import com.test.mvc.argument.converter.CsvHttpMessageConverter;
import com.test.mvc.argument.resolver.ArgDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

    @GetMapping(path = "list", produces = {CsvHttpMessageConverter.TEXT_CSV_VALUE, "application/*"})
    public List<ArgDemo> list() {
        return Arrays.asList(
                new ArgDemo("jack", 20),
                new ArgDemo("li", 18),
                new ArgDemo("peter", 30)
        );
    }
}

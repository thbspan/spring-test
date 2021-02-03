package com.test.mvc.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html")
public class HtmlDemoController {
    @Value("classpath:/static/test.html")
    private Resource testHtml;

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String test() throws IOException {
        return new String(Files.readAllBytes(Paths.get(testHtml.getURI())), StandardCharsets.UTF_8);
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE, path = "test")
    public String test2() {
        return "test";
    }
}

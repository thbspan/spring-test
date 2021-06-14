package com.test.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes(value = {"users"})
@RestController
public class RestDemoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestDemoController.class);


    @GetMapping(value = "/param")
    public Object index() {
        Map<String, Object> params = new HashMap<>();
        params.put("key", "hello");
        // throw new RuntimeException("exception test");
        LOGGER.info("params={}", params);
        return params;
    }

    /**
     * 映射请求头参数
     */
    @GetMapping(value = "/requestHeader")
    public String requestHeader(@RequestHeader(name = "user-agent", required = false) String userAgent,
                                HttpServletRequest request,
                                Map<String, Object> map) {
        // 保证session创建完成
        request.getSession(true);
        LOGGER.info("map class = {}", map.getClass());
        map.put("users", "test");
        return userAgent;
    }

    @GetMapping(value = "/sessionAttributes")
    public Object sessionAttributes(HttpSession session) {
        return session.getAttribute("users");
    }
}

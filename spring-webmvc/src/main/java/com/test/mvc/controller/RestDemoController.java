package com.test.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.test.mvc.editor.CustomMapPropertyEditor;

@SessionAttributes(value = {"users"})
@RestController
public class RestDemoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestDemoController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Map.class, new CustomMapPropertyEditor());
    }

    @GetMapping(value = "/param")
    public Object index() {
        Map<String, Object> params = new HashMap<>();
        params.put("key", "hello");
        // throw new RuntimeException("exception test");
        LOGGER.info("params={}", params);
        return params;
    }

    /**
     * {@code @RequestParam} 没有设置name，会将请求参数全部保存到 map 中；
     * 如果包含请求参数，会将指定name的值放到 map中
     */
    @GetMapping(value = "/map")
    public Object map(@RequestParam Map<String, Object> map) {
        return map;
    }

    /**
     * {@code @RequestParam} 没有设置name，会将请求参数全部保存到 map 中；
     * 如果包含请求参数，会将指定name的值放到 map中，但是没有相应的类型转换器，所有会报错，这里通过自定义来实现转换
     */
    @GetMapping(value = "/mapName")
    public Object mapName(@RequestParam(name = "id") Map<String, Object> map) {
        return map;
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

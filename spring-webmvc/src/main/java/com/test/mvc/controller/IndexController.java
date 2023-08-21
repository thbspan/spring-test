package com.test.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(ModelMap model) {
        model.put("test", "demo");
        return "index";
    }

    @GetMapping("/index")
    public void testDefaultViewName(ModelMap model) {
        model.put("test", "demo");
        // test default view name
    }
}

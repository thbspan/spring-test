package com.test.security.controller;

import javax.annotation.security.PermitAll;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/annotation")
public class AnnotationSecurityConfigDemoController {
    @PermitAll
    @GetMapping("/echo")
    public String demo() {
        return "echo example";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    /**
     * {@code @Secured @RolesAllowed @PreAuthorize} 这三个注解都可以配置访问权限、
     * 只是 {@code @EnableGlobalMethodSecurity}注解参数有些不同
     * - @Secured 需要配置 securedEnabled = true
     * - @RolesAllowed 需要配置 jsr250Enabled = true
     * - @PreAuthorize 需要配置 prePostEnabled = true
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("/normal")
    public String normal() {
        return "normal";
    }
}

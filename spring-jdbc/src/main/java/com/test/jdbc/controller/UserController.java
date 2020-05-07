package com.test.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.jdbc.dao.UserRepository;
import com.test.jdbc.domain.User;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String index() {
        return "user index";
    }

    @RequestMapping("/save")
    public Boolean save(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping("/list")
    public Object listAll() {
        return userRepository.listAll();
    }
}

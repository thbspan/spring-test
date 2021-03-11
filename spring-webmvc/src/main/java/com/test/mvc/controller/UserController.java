package com.test.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.mvc.service.UserService;
import com.test.mvc.vo.UserVO;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserVO> list() {
        return userService.listAll();
    }

    @GetMapping("/{id}")
    public UserVO get(@PathVariable("id") Integer id) {
        return userService.selectById(id);
    }

    @PostMapping
    public Integer add(UserVO user) {
        userService.addUser(user);
        return user.getId();
    }
}

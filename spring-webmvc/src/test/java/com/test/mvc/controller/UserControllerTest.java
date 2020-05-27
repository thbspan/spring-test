package com.test.mvc.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.mvc.SpringWebmvcApplicationTestBase;
import com.test.mvc.service.UserService;
import com.test.mvc.vo.UserVO;

public class UserControllerTest extends SpringWebmvcApplicationTestBase {

    @Autowired
    @InjectMocks
    private UserController userController;

    @Spy
    @Autowired
    private UserService userService;

    @BeforeEach
    public void init() {
        // ！！初始化
        MockitoAnnotations.initMocks(this);

        UserVO userA = new UserVO();
        userA.setId(1);
        userA.setUserName("A");

        UserVO userB = new UserVO();
        userB.setId(2);
        userB.setUserName("B");
        List<UserVO> users = Arrays.asList(userA, userB);
        Mockito.doReturn(users).when(userService).listAll();

        for (UserVO user : users) {
            Mockito.doReturn(user).when(userService).selectById(ArgumentMatchers.eq(user.getId()));
        }
    }

    @Test
    public void testList() {
        System.out.println(userController.list());
    }

    @Test
    public void testGet() {
        System.out.println(userController.get(1));

        System.out.println(userController.get(2));
    }
}

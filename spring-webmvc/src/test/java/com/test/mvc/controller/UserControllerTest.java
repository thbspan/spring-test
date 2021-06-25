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

    /**
     * mock 和 spy 区别：
     * 1、对于未执行mock的方法，spy会调用真实的方法，而mock默认不执行，返回默认值null
     * 2、使用方式不同 Mockito.when(restTemplate.postForObject(ArgumentMatchers.anyString(), ArgumentMatchers.any(), ...)
     * spy对直接调用方法；spy对象的使用方式，要先执行do等方法 Mockito.doReturn(info).when(authorizationInfoService).findAuthorizationInfo(appid);
     * mock对象也可以这样使用
     */
    @Spy
    @Autowired
    private UserService userService;

    @BeforeEach
    public void init() {
        // ！！初始化
        MockitoAnnotations.openMocks(this);

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

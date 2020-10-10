package com.test.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.xml.entity.User;
import com.test.xml.service.UserService;

public class XmlStarterApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = applicationContext.getBean(UserService.class);

        User user = new User();
        user.setName("老六");
        user.setAge(20);
        user.setSex("男");
        userService.save(user);
    }
}

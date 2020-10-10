package com.test.xml.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.xml.entity.User;
import com.test.xml.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) throws Exception {
        jdbcTemplate.update("insert into user(`name`, age, sex) values (?,?,?)",
                user.getName(), user.getAge(), user.getSex());
        // 测试 事务
        throw new RuntimeException("test transaction");
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

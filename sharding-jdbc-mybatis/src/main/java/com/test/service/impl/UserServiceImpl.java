package com.test.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import com.test.entity.User;
import com.test.mapper.UserMapper;
import com.test.service.ExampleService;

@Service("encrypt")
public class UserServiceImpl implements ExampleService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void initEnvironment() throws SQLException {
        userMapper.createTableIfNotExists();
        userMapper.truncateTable();
    }

    @Override
    public void cleanEnvironment() throws SQLException {
        userMapper.dropTable();
    }

    @Override
    public void processSuccess() throws SQLException {
        System.out.println("-------------- Process Success Begin ---------------");
        List<Long> userIds = insertData();
        printData();
        deleteData(userIds);
        printData();
        System.out.println("-------------- Process Success Finish --------------");
    }

    private List<Long> insertData() throws SQLException {
        System.out.println("---------------------------- Insert Data ----------------------------");
        List<Long> result = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName("test_mybatis_" + i);
            user.setPwd("pwd_mybatis_" + i);
            userMapper.insert(user);
            result.add((long) user.getUserId());
        }
        return result;
    }

    private void deleteData(final List<Long> userIds) throws SQLException {
        System.out.println("---------------------------- Delete Data ----------------------------");
        for (Long each : userIds) {
            userMapper.delete(each);
        }
    }

    @Override
    public void processFailure() throws SQLException {
        System.out.println("-------------- Process Failure Begin ---------------");
        insertData();
        System.out.println("-------------- Process Failure Finish --------------");
        throw new RuntimeException("Exception occur for transaction test.");
    }

    @Override
    public void printData() throws SQLException {
        System.out.println("---------------------------- Print User Data -----------------------");
        for (Object each : userMapper.selectAll()) {
            System.out.println(each);
        }
    }
}

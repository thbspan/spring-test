package com.test.mvc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.mvc.service.UserService;
import com.test.mvc.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserVO> listAll() {
        return null;
    }

    @Override
    public UserVO selectById(int id) {
        UserVO userVO = new UserVO();
        userVO.setId(id);
        userVO.setUserName("random " + id);
        return userVO;
    }

    @Override
    public void addUser(UserVO user) {

    }
}

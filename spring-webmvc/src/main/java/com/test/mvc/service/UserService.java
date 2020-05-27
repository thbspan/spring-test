package com.test.mvc.service;

import java.util.List;

import com.test.mvc.vo.UserVO;

public interface UserService {

    List<UserVO> listAll();

    UserVO selectById(int id);

    void addUser(UserVO user);
}

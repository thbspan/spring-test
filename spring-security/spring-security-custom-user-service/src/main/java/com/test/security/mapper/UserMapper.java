package com.test.security.mapper;

import com.test.security.entity.User;

public interface UserMapper {

    User findUserByUsername(String username);
}

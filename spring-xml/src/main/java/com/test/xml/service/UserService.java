package com.test.xml.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.xml.entity.User;

public interface UserService {

    @Transactional(propagation = Propagation.REQUIRED, timeout = 10)
    void save(User user) throws Exception;
}

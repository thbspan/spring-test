package com.test.annotation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessService.class);

    public BusinessService() {
        LOGGER.info("=================== init business service");
    }

    public int work(int i) {
        int result = 945 / i;
        LOGGER.info("=================== run work");
        return result;
    }
}

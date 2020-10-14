package com.test.annotation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
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

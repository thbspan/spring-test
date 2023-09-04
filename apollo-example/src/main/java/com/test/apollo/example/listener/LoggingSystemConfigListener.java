package com.test.apollo.example.listener;

import java.util.Set;

import jakarta.annotation.Resource;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Component;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

@Component
public class LoggingSystemConfigListener {
    /**
     * 日志配置项的前缀
     */
    private static final String LOGGER_TAG = "logging.level.";

    @Resource
    private LoggingSystem loggingSystem;

    @ApolloConfig
    private Config config;

    @ApolloConfigChangeListener
    public void onChange(ConfigChangeEvent changeEvent) {
        // 获取Apollo所有配置项的名称
        Set<String> propertyNames = config.getPropertyNames();
        for (String propertyName : propertyNames) {
            if (propertyName.startsWith(LOGGER_TAG)) {
                LogLevel level = LogLevel.valueOf(config.getProperty(propertyName, "info").toUpperCase());
                // 设置日志级别
                loggingSystem.setLogLevel(propertyName.replace(LOGGER_TAG, ""), level);
            }
        }
    }
}

package com.test.sentinel.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.datasource.apollo.ApolloDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class SentinelConfiguration {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public ApolloDataSource<List<FlowRule>> apolloDataSource(ObjectMapper objectMapper) {
        // ====Apollo 配置。暂时写死在代码里面
        // 项目编号。一般情况下，推荐和 spring.application.name 保持一致
        String appId = applicationName;
        // Apollo Meta 服务器地址
        String serverAddress = "http://localhost:8080";
        // Apollo 命名空间
        String namespace = "application";
        // Apollo 配置项的 KEY
        String flowRuleKey = "sentinel-example.flow-rule";

        // 创建 ApolloDataSource 对象
        System.setProperty("app.id", appId);
        System.setProperty("apollo.meta", serverAddress);
        // <X> 转换器，将读取的 Apollo 配置，转换成 FlowRule 数组
        ApolloDataSource<List<FlowRule>> apolloDataSource = new ApolloDataSource<>(namespace, flowRuleKey, "",
                value -> {
                    try {
                        return Arrays.asList(objectMapper.readValue(value, FlowRule[].class));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });

        // 注册到 FlowRuleManager 中
        FlowRuleManager.register2Property(apolloDataSource.getProperty());
        return apolloDataSource;
    }
}

package com.test.nacos.example;

import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Executor;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

public class NacosJavaSdk {

    public static void main(String[] args) throws NacosException {
        String serverAddr = "127.0.0.1:8848";
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        String dataId = "example";
        String groupId = "DEFAULT_GROUP";

        // NacosConfigService
        ConfigService configService = NacosFactory.createConfigService(properties);
//        String content = configService.getConfig(dataId, groupId, 5000);
//        System.out.println(content);
        configService.addListener(dataId, groupId, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("configInfo " + configInfo);
            }
        });
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.nextLine();
        }
    }
}

package com.test.nacos.example;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;

public class NacosJavaSdk {

    public static void main(String[] args) throws NacosException {
        String serverAddr = "127.0.0.1:8848";
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, "caab3dcc-d811-4a49-90e4-0d2eeafc44aa");
        properties.put(PropertyKeyConst.USERNAME, "nacos");
        properties.put(PropertyKeyConst.PASSWORD, "nacos");
        String dataId = "example.properties";
        String groupId = "DEFAULT_GROUP";

        // NacosConfigService
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, groupId, 5000);
        System.out.println(content);
//        configService.addListener(dataId, groupId, new Listener() {
//            @Override
//            public Executor getExecutor() {
//                return null;
//            }
//
//            @Override
//            public void receiveConfigInfo(String configInfo) {
//                System.out.println("configInfo " + configInfo);
//            }
//        });
//        try (Scanner scanner = new Scanner(System.in)) {
//            scanner.nextLine();
//        }
    }
}

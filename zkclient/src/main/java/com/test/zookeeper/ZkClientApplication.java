package com.test.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.zookeeper.config.listener.RegistryPropertyApplicationEnvironmentPreparedEventListener;

@SpringBootApplication
public class ZkClientApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ZkClientApplication.class);
        springApplication.addListeners(new RegistryPropertyApplicationEnvironmentPreparedEventListener());
        springApplication.run(args);
    }
}

package com.test.grpc.example.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.grpc.example.user.api.UserServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Configuration
public class GrpcConfig {
    private static final Integer GRPC_PORT = 8888;

    @Bean
    public ManagedChannel userGrpcManagedChannel() {
        return ManagedChannelBuilder.forAddress("127.0.0.1", GRPC_PORT).usePlaintext().build();
    }

    @Bean
    public UserServiceGrpc.UserServiceBlockingStub userServiceGrpc() {
        // 创建 ManagedChannel 对象
        ManagedChannel userGrpcManagedChannel = this.userGrpcManagedChannel();
        // 创建 UserServiceGrpc 对象
        return UserServiceGrpc.newBlockingStub(userGrpcManagedChannel);
    }
}
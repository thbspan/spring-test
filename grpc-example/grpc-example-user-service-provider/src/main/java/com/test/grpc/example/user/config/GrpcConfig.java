package com.test.grpc.example.user.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.grpc.example.user.api.impl.UserServiceGrpcImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@Configuration
public class GrpcConfig {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * GRPC Server 端口
     */
    private static final Integer GRPC_PORT = 8888;

    @Bean
    public Server grpcServer(final UserServiceGrpcImpl userServiceGrpc) throws IOException {
        // 创建 gRPC Server 对象
        Server server = ServerBuilder.forPort(GRPC_PORT)
                .addService(userServiceGrpc)
                .build();
        // 启动 gRPC Server
        server.start();
        LOGGER.info("[grpcServer][started，port=({})]", server.getPort());
        return server;
    }
}

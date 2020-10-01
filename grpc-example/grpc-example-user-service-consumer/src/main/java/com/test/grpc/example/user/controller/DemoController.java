package com.test.grpc.example.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.grpc.example.user.api.UserCreateRequest;
import com.test.grpc.example.user.api.UserCreateResponse;
import com.test.grpc.example.user.api.UserGetRequest;
import com.test.grpc.example.user.api.UserGetResponse;
import com.test.grpc.example.user.api.UserServiceGrpc;

import net.devh.boot.grpc.client.inject.GrpcClient;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GrpcClient("grpc-example-user-service-provider")
    private UserServiceGrpc.UserServiceBlockingStub userServiceGrpc;

    @GetMapping("/get")
    public String get(@RequestParam("id") Integer id) {
        // 创建请求
        UserGetRequest request = UserGetRequest.newBuilder().setId(id).build();
        // 执行 gRPC 请求
        UserGetResponse response = userServiceGrpc.get(request);
        // 响应
        return response.getName();
    }

    @PostMapping("/create")
    public Integer create(@RequestParam("name") String name,
                          @RequestParam("gender") Integer gender) {
        // 创建请求
        UserCreateRequest request = UserCreateRequest.newBuilder()
                .setName(name).setGender(gender).build();
        // 执行 gRPC 请求
        UserCreateResponse response = userServiceGrpc.create(request);
        // 响应
        return response.getId();
    }
}

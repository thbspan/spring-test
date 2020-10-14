package com.test.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkerService {
    @Autowired
    private BusinessService businessService;

    public void work() {
        System.out.println(businessService.work(3));
    }
}

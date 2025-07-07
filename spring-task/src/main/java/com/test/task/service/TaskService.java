package com.test.task.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    @Async
    public void run() throws InterruptedException {
        LOGGER.info("task run...");
        Thread.sleep(5000);
        LOGGER.info("task end...");
    }
}

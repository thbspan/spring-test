package com.test.task.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTask {

    @Scheduled(cron = "0/1 * * * * ?")
    public void doTask() throws InterruptedException {
        System.out.println("do mock task 1");
        TimeUnit.SECONDS.sleep(3);
        throw new RuntimeException("mock task 1 error");
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void doTask2() throws InterruptedException {
        System.out.println("do mock task 2");
        TimeUnit.SECONDS.sleep(3);
        throw new RuntimeException("mock task 2 error");
    }
}

package com.test.service;

import java.util.concurrent.ThreadLocalRandom;

public class LongRunningProcess implements Runnable{
    @Override
    public void run() {
        try {
            int millis = ThreadLocalRandom.current().nextInt(2000);
            String currentThread = Thread.currentThread().getName();
            System.out.println(currentThread + " sleep for " + millis + "milliseconds.");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

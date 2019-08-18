package com.test.polling.controller;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callable")
public class CallableController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallableController.class);

    @RequestMapping("/get")
    public Callable<Map<String, Object>> get(String id) {
        Callable<Map<String, Object>> callable = () -> {
            Thread.sleep(5000);

            LOGGER.info("callable task exec done!");

            return Collections.singletonMap("id", id);
        };
        LOGGER.info("controller exec done!");

        return callable;
    }
}

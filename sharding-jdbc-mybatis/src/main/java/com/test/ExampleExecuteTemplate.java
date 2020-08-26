package com.test;

import java.sql.SQLException;

import com.test.service.ExampleService;

public class ExampleExecuteTemplate {

    public static void run(final ExampleService exampleService) throws SQLException {
        try {
            exampleService.initEnvironment();
            exampleService.processSuccess();
        } finally {
            exampleService.cleanEnvironment();
        }
    }

    public static void runFailure(final ExampleService exampleService) throws SQLException {
        try {
            exampleService.initEnvironment();
            exampleService.processFailure();
        } finally {
            exampleService.cleanEnvironment();
        }
    }
}

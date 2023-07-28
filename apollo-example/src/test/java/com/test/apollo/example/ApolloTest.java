package com.test.apollo.example;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ApolloTest {

    @Test
    void test() {
        Config config = ConfigService.getAppConfig(); //config instance is singleton for each namespace and is never null
        String someKey = "hello";
        String someDefaultValue = "800";
        String value = config.getProperty(someKey, someDefaultValue);
        System.out.println("value=" + value);
        Assertions.assertNotNull(value);
    }
}

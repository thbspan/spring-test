package com.test.prop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.prop.entity.MailInfo;

@SpringBootTest(classes = {ConfigurationPropertiesApplication.class})// 指定启动类
public class ConfigurationPropertiesApplicationTests {

    @Autowired
    private MailInfo mailInfo;
    @Test
    public void testMailInfo() {
        Assertions.assertNotNull(mailInfo);
        System.out.println(mailInfo);
    }
}

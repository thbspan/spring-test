package com.test.prop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.prop.entity.AppProperties;
import com.test.prop.entity.MailInfo;
import com.test.prop.entity.Person;

@SpringBootTest(classes = {ConfigurationPropertiesApplication.class})// 指定启动类
class ConfigurationPropertiesApplicationTests {

    @Autowired
    private MailInfo mailInfo;
    @Autowired
    private Person person;
    @Autowired
    private AppProperties appProperties;
    @Test
    void testProp() {
        Assertions.assertNotNull(mailInfo);
        System.out.println(mailInfo);

        System.out.println(person.getPhone().getNumber());
        System.out.println(appProperties.getConnectTimeout());
        System.out.println(appProperties.getAuth());
    }
}

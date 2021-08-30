package com.test.validation;

import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

import com.test.validation.pojo.DemoUser;

public class HibernateValidatorTest {

    @Test
    public void testSPIValidationProvider() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        DemoUser user = new DemoUser();
        DemoUser.Address address = new DemoUser.Address();
        address.setProvince("北京市");
        address.setRegion("朝阳区");
        user.setAddressList(Collections.singletonList(address));
        Set<ConstraintViolation<DemoUser>> constraintViolationSet = validator.validate(user);
        for (ConstraintViolation<DemoUser> constraintViolation : constraintViolationSet) {
            System.out.println(constraintViolation.getMessage());
        }
    }
}

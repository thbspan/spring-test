package com.test.validation;

import com.test.validation.pojo.DemoUser;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

class HibernateValidatorTest {

    @Test
    void testSPIValidationProvider() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = validatorFactory.getValidator();
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
}

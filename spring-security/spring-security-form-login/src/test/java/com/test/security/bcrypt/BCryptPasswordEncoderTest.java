package com.test.security.bcrypt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class BCryptPasswordEncoderTest {

    @Test
    void testEncode() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Assertions.assertNotEquals(encoder.encode("admin"), encoder.encode("admin"));
        String userEncode = encoder.encode("user");
        Assertions.assertTrue(new BCryptPasswordEncoder().matches("user", userEncode));
        System.out.println(userEncode);
    }
}

package com.test.xml;

import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.transaction.annotation.Transactional;

import com.test.xml.mapper.UserRowMapper;

public class AnnotationUtilsTest {

    @Test
    public void testIsCandidateClass() {
        System.out.println(AnnotationUtils.isCandidateClass(UserRowMapper.class, Transactional.class));
    }
}

package com.test.mvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.test.mvc.SpringWebmvcApplicationTestBase;

@AutoConfigureMockMvc
public class MockMvcTest extends SpringWebmvcApplicationTestBase {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetUserById() throws Exception {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/users/2"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        MockMvcResultMatchers.content().json("{\"id\":2,\"userName\":\"random 2\",\"age\":0}");

        resultActions.andDo(MockMvcResultHandlers.print());
        // 获取结果
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println(mvcResult);
    }
}

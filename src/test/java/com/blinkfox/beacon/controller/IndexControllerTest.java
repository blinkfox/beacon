package com.blinkfox.beacon.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * IndexControllerTest.
 *
 * @author blinkfox on 2019-03-30.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(IndexController.class)
public class IndexControllerTest {

    @Resource
    private MockMvc mockMvc;

    /**
     * 测试首页的访问接口.
     *
     * @throws Exception 异常
     */
    @Test
    public void index() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    /**
     * 测试返回普通字符串数据的示例接口.
     *
     * @throws Exception 异常
     */
    @Test
    public void say() throws Exception {
        this.mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Beacon!"));
    }

    /**
     * 测试有测试和有异常情况普通字符串数据的示例接口.
     *
     * @throws Exception 异常
     */
    @Test
    public void exception() throws Exception {
        this.mockMvc.perform(get("/exception?name=Zhangsan"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Zhangsan"));

        this.mockMvc.perform(get("/exception?name="))
                .andExpect(status().isInternalServerError());
    }

}

package com.blinkfox.beacon.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.blinkfox.beacon.service.BadgeService;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * BadgeControllerTest.
 *
 * @author blinkfox on 2019-03-30.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BadgeController.class)
public class BadgeControllerTest {

    /**
     * 一个用于测试的svg字符串.
     */
    private static final String TEST_SVG = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"100%\" height=\"100%\" "
            + "version=\"1.1\"><rect width=\"300\" height=\"100\" style=\"fill:rgb(0,0,255);stroke-width:1; "
            + "stroke:rgb(0,0,0)\"/></svg>";

    @Resource
    private MockMvc mockMvc;

    @MockBean
    private BadgeService badgeService;

    /**
     * 测试获取徽章的方法.
     */
    @Before
    public void init() {
        Mockito.when(badgeService.generate(Mockito.any())).thenReturn(TEST_SVG);
    }

    /**
     * 测试使用`-`来获取徽章的方法.
     */
    @Test
    public void getBadgeByDash() throws Exception {
        this.mockMvc.perform(get("/badge/hello-world-blue.svg")).andExpect(status().isOk());
    }

    /**
     * 测试使用`/`来获取徽章的方法.
     */
    @Test
    public void getBadgeBySlash() throws Exception {
        this.mockMvc.perform(get("/badge/hello/my-world/red.svg")).andExpect(status().isOk());
    }

}

package com.hks.spring_boot_hibernate.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

/**
 * @Author: xiaohe
 * @Date: 2018/7/21 22:34
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void selectUser() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/selectUser"))
                .andExpect(MockMvcResultMatchers.status().isOk()) //路径是否正确
                .andExpect(MockMvcResultMatchers.content().string("abc"));//返回值比较
    }
}
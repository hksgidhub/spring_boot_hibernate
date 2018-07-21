package com.hks.spring_boot_hibernate.service;

import com.hks.spring_boot_hibernate.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: xiaohe
 * @Date: 2018/7/21 22:16
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void findOne() {
        Object user = userService.findOne("sd23432");
        //Assert.assertEquals(new Integer(23),user.getAge());
    }
}
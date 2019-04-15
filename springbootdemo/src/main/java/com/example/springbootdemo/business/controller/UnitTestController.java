package com.example.springbootdemo.business.controller;

import com.example.springbootdemo.startup.SpringbootdemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:单元测试
 * @Author: liuweixin
 * @Date: 2019/3/28
 * @Time: 20:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootdemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UnitTestController {

    @Test
    public void testM(){
        System.out.println("test hello~");
    }
}

package com.example.springbootdemo.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: liuweixin
 * @Date: 2019/3/29
 * @Time: 10:56
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @RequestMapping("/login")
    public String userLogin(){
        return "demo-sign";
    }

    @RequestMapping("/login-error")
    public String loginError(){
        return "login-error";
    }

    @RequestMapping("/login/form")
    @ResponseBody
    public String userLoginForm(){
        return "Login Success";
    }
}

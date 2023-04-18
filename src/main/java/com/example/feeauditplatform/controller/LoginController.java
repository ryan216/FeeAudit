package com.example.feeauditplatform.controller;

import com.example.feeauditplatform.domain.User;
import com.example.feeauditplatform.service.LoginServcie;
import com.example.feeauditplatform.service.UserService;
import com.example.feeauditplatform.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 你与黎明
 * @Description: 登陆控制
 * @create: 2022-10-04 16:26
 * @Version: 1.0
 */
@RestController
@RequestMapping("user")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    LoginServcie loginServcie;


    @PostMapping("/login")
    public R login(@RequestBody User user)  {
        //登录
//        return userService.login(user);
        return loginServcie.login(user);
    }


    @RequestMapping("/logout")
    public R logout(){

//        return userService.logout();
        return loginServcie.logout();
    }
}

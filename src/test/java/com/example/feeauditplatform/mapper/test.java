package com.example.feeauditplatform.mapper;

import com.example.feeauditplatform.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description: usertest
 * @Author: Ryan
 * @Date: 2023/4/18 17:55
 * @Version: 1.0
 */
@SpringBootTest
public class test {

    @Autowired
    private UserMapper userMapper;



    @Test
    public void test_PasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String pd="123456";
        String code1=passwordEncoder.encode(pd);
        User user =new User();
        user.setUsername("user");
        user.setPassword(code1);
        System.out.println(userMapper.insert(user));

//        System.out.println(passwordEncoder.matches("123456",
//                "$2a$10$znwj9INTVwDAHnY8LpGPDOVeJAtlTX2F1Z7E9iVNVwBr.GZw.cjYW"));
//        System.out.println(code1);
//        System.out.println(code2);
    }
}

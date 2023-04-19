package com.example.feeauditplatform.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.feeauditplatform.domain.User;
import com.example.feeauditplatform.mapper.UserMapper;
import com.example.feeauditplatform.service.UserService;
import com.example.feeauditplatform.utils.JwtUtil;
import com.example.feeauditplatform.utils.R;
import com.example.feeauditplatform.utils.RedisCache;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



/**
 * @Author: 你与黎明
 * @Description: userserviceimpl
 * @create: 2022-10-03 22:10
 * @Version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;


    @Autowired
    private RedisCache redisCache;




    @Override
    public User getByName(String username) {
        return userMapper.getByName(username);
    }



    @Override
    public R getUserInfo(String authorization) {
        if (!org.springframework.util.StringUtils.hasText(authorization)) {
            //放行
            return new R("token非法",false);
        }
        //解析token
        String userid;
        String token = null;
        try {
            String []tokens=authorization.split(" ");
            if(tokens.length>1){
                token = tokens[1];
            }
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            return new R("token非法",false);
        }
        User user = userMapper.selectById(userid);
        if(user!=null){
            user.setPassword(null);

            return new R("用户信息获取成功！",user,true);
        }
        return new R("用户信息获取失败！",null,true);

    }

    @Override
    public R changeUserInfo(String authorization, User user) {
        if (!org.springframework.util.StringUtils.hasText(authorization)) {
            //放行
            return new R("token非法",false);
        }
        //解析token
        String userid;
        String token = null;
        try {
            String []tokens=authorization.split(" ");
            if(tokens.length>1){
                token = tokens[1];
            }
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            return new R("token非法",false);
        }
        int i = userMapper.updateById(user);
        if(i==1){
            return new R("用户信息修改成功！",null,true);
        }
        return new R("用户信息修改失败！",null,true);

    }

    @Override
    public R DeleteUser(String authorization, User user) {
        if (!org.springframework.util.StringUtils.hasText(authorization)) {
            //放行
            return new R("token非法",false);
        }
        //解析token
        String userid;
        String token = null;
        try {
            String []tokens=authorization.split(" ");
            if(tokens.length>1){
                token = tokens[1];
            }
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            return new R("token非法",false);
        }
        int i = userMapper.deleteById(user);
        if(i==1){
            return new R("用户删除成功！",null,true);
        }
        return new R("用户删除失败！",null,true);

    }


}

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
    public R login(User user) {
        String pd=user.getPassword();
        String username=user.getUsername();
        User user1 = userMapper.getByName(username );
        if(user1==null){
            return new R("用户名不存在，登陆失败",false);
        }
        if(!pd.equals(user1.getPassword())){
            return new R("密码错误，登陆失败",false);
        }
        user1.setPassword(null);
        return new R("登陆成功",user1,true);
    }

    @Override
    public R logout() {
        return null;
    }


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

//        String redisKey = "login:" + userid;
//        LoginUser loginUser = redisCache.getCacheObject(redisKey);
//        User user = loginUser.getUser();
        User user = userMapper.selectById(userid);
        user.setPassword(null);

        return new R("用户信息获取成功！",user,true);
    }




}

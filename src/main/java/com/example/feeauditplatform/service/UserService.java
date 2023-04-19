package com.example.feeauditplatform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.feeauditplatform.domain.User;
import com.example.feeauditplatform.utils.R;


/**
 * @Author: 你与黎明
 * @Description: userservice interface
 * @create: 2022-10-03 22:07
 * @Version: 1.0
 */
public interface UserService extends IService<User> {


    /** 
     * @description:
     * @param username String
     * @return: User
    **/
    User getByName(String username);




    public  R getUserInfo(String authorization);



    public  R changeUserInfo(String authorization,User user);


    public  R DeleteUser(String authorization,User user);

}

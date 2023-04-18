package com.example.feeauditplatform.service;


import com.example.feeauditplatform.domain.User;
import com.example.feeauditplatform.utils.R;

public interface LoginServcie {

    R login(User user);

    R logout();

}

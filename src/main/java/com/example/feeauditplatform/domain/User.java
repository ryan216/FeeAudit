package com.example.feeauditplatform.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 你与黎明
 * @Description: User entity
 * @create: 2022-11-15
 * @Version: 1.0
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private  String role;

}

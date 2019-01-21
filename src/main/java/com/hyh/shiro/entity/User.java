package com.hyh.shiro.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String sex;
    private Integer age;
    private String phone;
    private String status;
    private Role role;

}

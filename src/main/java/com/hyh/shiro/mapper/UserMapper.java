package com.hyh.shiro.mapper;

import com.hyh.shiro.entity.User;

import java.util.List;

public interface UserMapper {

    User getUserByAccount(String account);

    List<User> list();

    void addUser(User user);

    User getUserById(User user);

    void updateUser(User user);

    void deleteUser(User user);
}

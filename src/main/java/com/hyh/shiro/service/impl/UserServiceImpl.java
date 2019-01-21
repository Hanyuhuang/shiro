package com.hyh.shiro.service.impl;

import com.hyh.shiro.entity.User;
import com.hyh.shiro.mapper.UserMapper;
import com.hyh.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }

    public List<User> list() {
        return userMapper.list();
    }

    public void addUser(User user) {
        userMapper.addUser(user);
    }

    public User getUserById(User user) {
        return userMapper.getUserById(user);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(User user) {
        userMapper.deleteUser(user);
    }
}

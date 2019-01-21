package com.hyh.shiro.controller;

import com.hyh.shiro.entity.User;
import com.hyh.shiro.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    UserService userService;

    @RequiresPermissions("user:select")
    @RequestMapping("list")
    public String list(ModelMap map){
        List<User> list = userService.list();
        map.put("list",list);
        return "list";
    }

    @RequiresPermissions("user:add")
    @RequestMapping("toAddUser")
    public String toAddUser(){
        return "addUser";
    }

    @RequiresPermissions("user:add")
    @RequestMapping("addUser")
    public String addUser(User user){
        // 密码加密
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
        Object password = new SimpleHash("MD5", user.getPassword(), credentialsSalt, 1024);
        user.setPassword(password.toString());
        userService.addUser(user);
        return "redirect:/list";
    }

    @RequiresPermissions("user:update")
    @RequestMapping("toUpdateUser")
    public String toUpdateUser(User user,ModelMap map){
        user = userService.getUserById(user);
        map.put("user",user);
        return "updateUser";
    }

    @RequiresPermissions("user:update")
    @RequestMapping("updateUser")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/list";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping("deleteUser")
    public String deleteUser(User user){
        userService.deleteUser(user);
        return "redirect:/list";
    }

}

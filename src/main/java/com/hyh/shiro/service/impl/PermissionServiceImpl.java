package com.hyh.shiro.service.impl;

import com.hyh.shiro.entity.Permission;
import com.hyh.shiro.entity.Role;
import com.hyh.shiro.mapper.PermissionMapper;
import com.hyh.shiro.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    public List<Permission> listPermissionByRole(Role role) {
        return permissionMapper.listPermissionByRole(role);
    }
}

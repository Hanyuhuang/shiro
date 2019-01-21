package com.hyh.shiro.service;

import com.hyh.shiro.entity.Permission;
import com.hyh.shiro.entity.Role;

import java.util.List;

public interface PermissionService {


    List<Permission> listPermissionByRole(Role role);
}

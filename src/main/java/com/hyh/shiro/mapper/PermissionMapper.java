package com.hyh.shiro.mapper;

import com.hyh.shiro.entity.Permission;
import com.hyh.shiro.entity.Role;

import java.util.List;

public interface PermissionMapper {

    List<Permission> listPermissionByRole(Role role);

}

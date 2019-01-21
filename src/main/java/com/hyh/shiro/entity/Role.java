package com.hyh.shiro.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Role {

    private Integer id;
    private String name;
    private String description;
    private List<Permission> permission;
}

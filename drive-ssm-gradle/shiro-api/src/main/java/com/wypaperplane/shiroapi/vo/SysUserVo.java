package com.wypaperplane.shiroapi.vo;

import com.wypaperplane.shiroapi.entity.SysUser;

import java.time.LocalDateTime;

public class SysUserVo extends SysUser {
    private String[] roleNames;

    public SysUserVo() {
        super();
    }

    public SysUserVo(
            Integer id,
            Byte sort,
            String username,
            String password,
            String avatar,
            String mobile,
            String email,
            Boolean deleted,
            Integer[] roleIds,
            LocalDateTime addTime,
            LocalDateTime updatedTime,
            String[] roleNames
    ) {
        super(id, sort, username, password, avatar, mobile, email, deleted, roleIds, addTime, updatedTime);
        this.roleNames = roleNames;
    }

    public String[] getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String[] roleNames) {
        this.roleNames = roleNames;
    }
}

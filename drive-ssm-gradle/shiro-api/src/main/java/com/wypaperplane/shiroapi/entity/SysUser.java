package com.wypaperplane.shiroapi.entity;

import com.wypaperplane.syscore.mybatis.JsonIntegerArrayTypeHandler;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

public class SysUser implements Serializable, Cloneable {

    @Id
    private Integer id;

    @NotNull(message = "sort 为空或类型错误")
    private Byte sort;

    @NotEmpty(message = "username 为空或类型错误")
    @Column(name = "`username`")
    private String username;

    @NotEmpty(message = "password 为空或类型错误")
    @Column(name = "`password`")
    private String password;

    @NotNull(message = "avatar 为空或类型错误")
    private String avatar;

    @Pattern(regexp="^1[3-9]\\d{9}$", message = "手机号码错误")
    private String mobile;

    @NotNull(message = "email 为空或类型错误")
    private String email;

    private Boolean deleted;

    @NotNull(message = "roleIds 为空或类型错误")
    @ColumnType(
            column = "role_ids",
            jdbcType = JdbcType.VARCHAR,
            typeHandler = JsonIntegerArrayTypeHandler.class)
    private Integer[] roleIds;

    private LocalDateTime addTime;

    private LocalDateTime updatedTime;

    public SysUser() {}

    public SysUser(
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
            LocalDateTime updatedTime
    ) {
        this.id = id;
        this.sort = sort;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.mobile = mobile;
        this.email = email;
        this.deleted = deleted;
        this.roleIds = roleIds;
        this.addTime = addTime;
        this.updatedTime = updatedTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", sort=" + sort +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", deleted=" + deleted +
                ", roleIds=" + Arrays.toString(roleIds) +
                ", addTime=" + addTime +
                ", updatedTime=" + updatedTime +
                '}';
    }
}

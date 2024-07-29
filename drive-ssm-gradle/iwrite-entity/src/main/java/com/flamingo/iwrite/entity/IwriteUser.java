package com.flamingo.iwrite.entity;

import com.flamingo.iwrite.enumm.Gender;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class IwriteUser {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    private String mobile;
    private String email;
    private String password;
    private String avatar;
    private String nickname;

    private Gender gender;
    private String reallname;
    private String identityId;
    private Byte userLevel; // 0普通用户 1VIP用户 2高级VIP用户
    private Byte status; // 0可用 1禁用 2注销
    private Boolean deleted;
    private LocalDate birthday;
    private LocalDateTime addTime;
    private LocalDateTime updateTime;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;

    public IwriteUser() {}

    public IwriteUser(Integer id, String mobile, String email, String password, String avatar, String nickname, Gender gender, String reallname, String identityId, Byte userLevel, Byte status, Boolean deleted, LocalDate birthday, LocalDateTime addTime, LocalDateTime updateTime, LocalDateTime lastLoginTime, String lastLoginIp) {
        this.id = id;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.nickname = nickname;
        this.gender = gender;
        this.reallname = reallname;
        this.identityId = identityId;
        this.userLevel = userLevel;
        this.status = status;
        this.deleted = deleted;
        this.birthday = birthday;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.lastLoginTime = lastLoginTime;
        this.lastLoginIp = lastLoginIp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getReallname() {
        return reallname;
    }

    public void setReallname(String reallname) {
        this.reallname = reallname;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public Byte getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Byte userLevel) {
        this.userLevel = userLevel;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }
}

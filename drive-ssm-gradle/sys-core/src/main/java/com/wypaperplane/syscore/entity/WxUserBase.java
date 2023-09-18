package com.wypaperplane.syscore.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import javax.persistence.Id;
import java.io.Serializable;

public class WxUserBase implements Serializable, Cloneable {

    @Id
    private Integer id;

    @NotEmpty(message = "avatar 不能为空字符串")
    private String avatar;

    @NotEmpty(message = "nickname 不能为空字符串")
    private String nickname;

    @NotEmpty(message = "reallname 不能为空字符串")
    private String reallname;

    @NotNull(message = "gender 不能为空字符串")
    private Byte gender; // 0男, 1女

    @Pattern(regexp="^1[3-9]\\d{9}$", message = "手机号码错误")
    private String mobile;

    public WxUserBase() {}

    public WxUserBase(Integer id, String avatar, String nickname, String reallname, Byte gender, String mobile) {
        this.id = id;
        this.avatar = avatar;
        this.nickname = nickname;
        this.reallname = reallname;
        this.gender = gender;
        this.mobile = mobile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getReallname() {
        return reallname;
    }

    public void setReallname(String reallname) {
        this.reallname = reallname;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

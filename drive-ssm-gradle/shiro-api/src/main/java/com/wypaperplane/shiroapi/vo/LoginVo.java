package com.wypaperplane.shiroapi.vo;

import jakarta.validation.constraints.NotEmpty;

public class LoginVo {

    @NotEmpty(message = "username 不能为空字符串")
    private String username;

    @NotEmpty(message = "password 不能为空字符串")
    private String password;

    public LoginVo() {}

    public LoginVo(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

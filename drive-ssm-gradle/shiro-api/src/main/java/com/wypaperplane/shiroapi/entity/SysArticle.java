package com.wypaperplane.shiroapi.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import java.time.LocalDateTime;

public class SysArticle {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @NotNull(message = "userId 为空或类型错误")
    private Integer userId;

    @NotEmpty(message = "title 为空或类型错误")
    private String title;

    private String aContent;

    private LocalDateTime addTime;

    public SysArticle() {}

    public SysArticle(Integer id, Integer userId, String title, String aContent, LocalDateTime addTime) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.aContent = aContent;
        this.addTime = addTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getaContent() {
        return aContent;
    }

    public void setaContent(String aContent) {
        this.aContent = aContent;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }
}

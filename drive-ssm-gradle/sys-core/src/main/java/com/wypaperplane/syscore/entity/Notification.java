package com.wypaperplane.syscore.entity;

import org.hibernate.validator.constraints.Range;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class Notification implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @Column(name = "`user_id`")
    private Integer userId;

    /**
     * 0系统首页通知
     * */
    @Range(min = 1, max = 127, message = "nType 为空或类型错误")
    private Byte nType;

    @Column(name = "`n_content`")
    private String nContent;

    public Notification() {}

    public Notification(Integer id, Integer userId, Byte nType, String nContent) {
        this.id = id;
        this.userId = userId;
        this.nType = nType;
        this.nContent = nContent;
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

    public Byte getnType() {
        return nType;
    }

    public void setnType(Byte nType) {
        this.nType = nType;
    }

    public String getnContent() {
        return nContent;
    }

    public void setnContent(String nContent) {
        this.nContent = nContent;
    }
}

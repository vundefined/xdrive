package com.wypaperplane.drivewxmini.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TeacherCar implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @NotNull(message = "userId 不能为空字符串")
    private Integer wxUserId;

    @NotEmpty(message = "name 不能为空字符串")
    @Column(name = "`name`")
    private String name;

    @NotEmpty(message = "encode 不能为空字符串")
    private String encode;

    @NotEmpty(message = "img 不能为空字符串")
    private String img;

    public TeacherCar() {}

    public TeacherCar(Integer id, Integer wxUserId, String name, String encode, String img) {
        this.id = id;
        this.wxUserId = wxUserId;
        this.name = name;
        this.encode = encode;
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWxUserId() {
        return wxUserId;
    }

    public void setWxUserId(Integer wxUserId) {
        this.wxUserId = wxUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "TeacherCar{" +
                "id=" + id +
                ", wxUserId=" + wxUserId +
                ", name='" + name + '\'' +
                ", encode='" + encode + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}

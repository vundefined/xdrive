package com.wypaperplane.drivewxmini.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

public class QrRecommend implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @NotNull(message = "userId 不能为空字符串")
    private Integer teacherId;

    @NotNull(message = "classId 为空或类型错误")
    @Column(name = "`class_id`")
    private Integer classId;

    @NotNull(message = "price 为空或类型错误")
    private Integer price;

    public QrRecommend() {
    }

    public QrRecommend(Integer id, Integer teacherId, Integer classId, Integer price) {
        this.id = id;
        this.teacherId = teacherId;
        this.classId = classId;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

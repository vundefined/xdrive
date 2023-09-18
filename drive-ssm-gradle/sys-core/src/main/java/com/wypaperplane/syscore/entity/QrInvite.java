package com.wypaperplane.syscore.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

public class QrInvite implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @Column(name = "`teacher_id`")
    @NotNull(message = "userId 不能为空字符串")
    private Integer teacherId;

    @Range(min = 1, max = 127, message = "qrTime 为空或类型错误")
    private Byte qrTime;

    @Range(min = 1, max = 127, message = "qrCount 为空或类型错误")
    private  Byte qrCount;

    private String qrImg;

    private LocalDateTime updateTime;

    public QrInvite() {}

    public QrInvite(Integer id, Integer teacherId, Byte qrTime, Byte qrCount, String qrImg, LocalDateTime updateTime) {
        this.id = id;
        this.teacherId = teacherId;
        this.qrTime = qrTime;
        this.qrCount = qrCount;
        this.qrImg = qrImg;
        this.updateTime = updateTime;
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

    public Byte getQrTime() {
        return qrTime;
    }

    public void setQrTime(Byte qrTime) {
        this.qrTime = qrTime;
    }

    public Byte getQrCount() {
        return qrCount;
    }

    public void setQrCount(Byte qrCount) {
        this.qrCount = qrCount;
    }

    public String getQrImg() {
        return qrImg;
    }

    public void setQrImg(String qrImg) {
        this.qrImg = qrImg;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}

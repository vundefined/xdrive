package com.wypaperplane.syscore.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 学员与教练的绑定关系
 * */
public class TeacherOfStudent implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @NotNull(message = "teacherId 为空或类型错误")
    private Integer teacherId;

    @NotNull(message = "studentId 不能为空字符串")
    private Integer studentId;

    @NotNull(message = "recommend 为空或类型错误")
    private Integer recommend;

    @NotNull(message = "qrType 为空或类型错误")
    private Byte qrType; // 0教练邀请已在驾校交完费用的, 1教练自己推广的，2学员为教练推广的

    private LocalDateTime addTime;

    /**
     * 逻辑删除
     * 0false绑定，1true解绑
     * 暂时物理删除
    * */
    @NotNull(message = "deleted 为空或类型错误")
    private Boolean deleted;

    public TeacherOfStudent() {}

    public TeacherOfStudent(Integer id, Integer teacherId, Integer studentId, Integer recommend, Byte qrType, LocalDateTime addTime, Boolean deleted) {
        this.id = id;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.recommend = recommend;
        this.qrType = qrType;
        this.addTime = addTime;
        this.deleted = deleted;
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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public Byte getQrType() {
        return qrType;
    }

    public void setQrType(Byte qrType) {
        this.qrType = qrType;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}

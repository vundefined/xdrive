package com.wypaperplane.drivewxmini.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import java.io.Serializable;

public class StudentSettings implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @NotNull(message = "studentId 不能为空")
    private Integer studentId;

    @NotNull(message = "commonId 不能为空")
    private Integer commonId;

    /**
     * 0 默认 teacherId
     * 1 默认 classId
     * */
    @NotNull(message = "stype 不能为空")
    private Byte stype;

    public StudentSettings() {}

    public StudentSettings(Integer id, Integer studentId, Integer commonId, Byte stype) {
        this.id = id;
        this.studentId = studentId;
        this.commonId = commonId;
        this.stype = stype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCommonId() {
        return commonId;
    }

    public void setCommonId(Integer commonId) {
        this.commonId = commonId;
    }

    public Byte getStype() {
        return stype;
    }

    public void setStype(Byte stype) {
        this.stype = stype;
    }
}

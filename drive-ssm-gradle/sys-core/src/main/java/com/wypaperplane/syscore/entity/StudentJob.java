package com.wypaperplane.syscore.entity;

import com.wypaperplane.syscore.mybatis.JsonIntegerArrayTypeHandler;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

public class StudentJob implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @NotNull(message = "userId 不能为空字符串")
    private Integer studentId;

    @NotNull(message = "classId 为空或类型错误")
    @Column(name = "`class_id`")
    private Integer classId;

    @NotNull(message = "classIndex 为空或类型错误")
    @ColumnType(column = "class_index", jdbcType = JdbcType.VARCHAR, typeHandler = JsonIntegerArrayTypeHandler.class)
    private Integer[] classIndex;

    /*
    * 教练为学员备注信息
    * */
    @Column(name = "`desc`")
    private String desc;

    private LocalDateTime addTime;

    public StudentJob() {}

    public StudentJob(Integer id, Integer studentId, Integer classId, @NotNull(message = "classIndex 为空或类型错误") Integer[] classIndex, String desc, LocalDateTime addTime) {
        this.id = id;
        this.studentId = studentId;
        this.classId = classId;
        this.classIndex = classIndex;
        this.desc = desc;
        this.addTime = addTime;
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

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer[] getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(Integer[] classIndex) {
        this.classIndex = classIndex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }
}

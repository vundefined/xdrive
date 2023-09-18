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
import java.time.LocalTime;

public class AppointmentRecord implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @NotNull(message = "userId 不能为空字符串")
    private Integer studentId;

    @NotNull(message = "teacherId 为空或类型错误")
    private Integer teacherId;

    /*
     * 驾驶证类型
     * */
    @NotNull(message = "classId 为空或类型错误")
    private Integer classId;

    /*
     * 驾驶证类型
     * */
    @NotNull(message = "classIndex 为空或类型错误")
    @ColumnType(column = "class_index", jdbcType = JdbcType.VARCHAR, typeHandler = JsonIntegerArrayTypeHandler.class)
    private Integer[] classIndex;

    @NotNull(message = "duringId 为空或类型错误")
    private Integer duringId;

    @NotNull(message = "duringStartTime 为空或类型错误")
    @Column(name = "`during_start_time`")
    private LocalTime duringStartTime;

    @NotNull(message = "duringEndTime 为空或类型错误")
    @Column(name = "`during_end_time`")
    private LocalTime duringEndTime;

    @NotNull(message = "status 为空或类型错误")
    @Column(name = "`status`")
    private Byte status; // 0未审核, 1通过, 2未通过, 3已取消

    private LocalDateTime addTime;

    public AppointmentRecord() {}

    public AppointmentRecord(Integer id, Integer studentId, Integer teacherId, Integer classId, Integer[] classIndex, Integer duringId, LocalTime duringStartTime, LocalTime duringEndTime, Byte status, LocalDateTime addTime) {
        this.id = id;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.classId = classId;
        this.classIndex = classIndex;
        this.duringId = duringId;
        this.duringStartTime = duringStartTime;
        this.duringEndTime = duringEndTime;
        this.status = status;
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

    public Integer[] getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(Integer[] classIndex) {
        this.classIndex = classIndex;
    }

    public Integer getDuringId() {
        return duringId;
    }

    public void setDuringId(Integer duringId) {
        this.duringId = duringId;
    }

    public LocalTime getDuringStartTime() {
        return duringStartTime;
    }

    public void setDuringStartTime(LocalTime duringStartTime) {
        this.duringStartTime = duringStartTime;
    }

    public LocalTime getDuringEndTime() {
        return duringEndTime;
    }

    public void setDuringEndTime(LocalTime duringEndTime) {
        this.duringEndTime = duringEndTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }
}

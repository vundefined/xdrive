package com.wypaperplane.drivewxmini.entity;

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
import java.time.LocalTime;

public class AppointmentRule implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @Column(name = "`teacher_id`")
    @NotNull(message = "userId 不能为空字符串")
    private Integer teacherId;

    /*
     * 休息日
     * */
    @ColumnType(column = "rest", jdbcType = JdbcType.VARCHAR, typeHandler = JsonIntegerArrayTypeHandler.class)
    private Integer[] rest;

    @ColumnType(column = "rest_index", jdbcType = JdbcType.VARCHAR, typeHandler = JsonIntegerArrayTypeHandler.class)
    private Integer[] restIndex;

    @NotNull(message = "startTime 为空或类型错误")
    @Column(name = "`start_time`")
    private LocalTime startTime;

    @NotNull(message = "endTime 为空或类型错误")
    @Column(name = "`end_time`")
    private LocalTime endTime;

    @Column(name = "`rule_txt`")
    private String ruleTxt;

    public AppointmentRule() {}

    public AppointmentRule(Integer id, Integer teacherId, Integer[] rest, Integer[] restIndex, LocalTime startTime, LocalTime endTime, String ruleTxt) {
        this.id = id;
        this.teacherId = teacherId;
        this.rest = rest;
        this.restIndex = restIndex;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ruleTxt = ruleTxt;
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

    public Integer[] getRest() {
        return rest;
    }

    public void setRest(Integer[] rest) {
        this.rest = rest;
    }

    public Integer[] getRestIndex() {
        return restIndex;
    }

    public void setRestIndex(Integer[] restIndex) {
        this.restIndex = restIndex;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getRuleTxt() {
        return ruleTxt;
    }

    public void setRuleTxt(String ruleTxt) {
        this.ruleTxt = ruleTxt;
    }
}

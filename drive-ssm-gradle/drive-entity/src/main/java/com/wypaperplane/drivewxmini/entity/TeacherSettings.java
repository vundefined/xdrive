package com.wypaperplane.drivewxmini.entity;

import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import java.io.Serializable;

public class TeacherSettings implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @NotNull(message = "teacherId 为空或类型错误")
    private Integer teacherId;

    /**
     * 多长时间之后执行分账操作, 单位为毫秒
     * */
    @ColumnType(column = "delay_time", jdbcType = JdbcType.INTEGER)
    private Integer delayTime;

    /**
     * 执行分账的比例
     * */
    private Byte percentage;

    public TeacherSettings() {}

    public TeacherSettings(Integer id, Integer teacherId, Integer delayTime, Byte percentage) {
        this.id = id;
        this.teacherId = teacherId;
        this.delayTime = delayTime;
        this.percentage = percentage;
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

    public Integer getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }

    public Byte getPercentage() {
        return percentage;
    }

    public void setPercentage(Byte percentage) {
        this.percentage = percentage;
    }
}

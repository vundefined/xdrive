package com.wypaperplane.syscore.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalTime;

public class AppointmentDuring implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @Column(name = "`wx_user_id`")
    @NotNull(message = "userId 不能为空字符串")
    private Integer wxUserId;

    @NotNull(message = "startTime 为空或类型错误")
    @Column(name = "`start_time`")
    private LocalTime startTime;

    @NotNull(message = "endTime 为空或类型错误")
    @Column(name = "`end_time`")
    private LocalTime endTime;

    private Boolean checked;

    public AppointmentDuring() {}

    public AppointmentDuring(Integer id, Integer wxUserId, LocalTime startTime, LocalTime endTime, Boolean checked) {
        this.id = id;
        this.wxUserId = wxUserId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.checked = checked;
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

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}

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

public class AppointmentClassDuring implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @Column(name = "`wx_user_id`")
    @NotNull(message = "userId 不能为空字符串")
    private Integer wxUserId;

    /*
     * 驾驶证类型
     * */
    @NotNull(message = "classId 为空或类型错误")
    @Column(name = "`class_id`")
    private Integer classId;

    /*
     * 驾驶证类型
     * */
    @NotNull(message = "classIndex 为空或类型错误")
    @ColumnType(column = "class_index", jdbcType = JdbcType.VARCHAR, typeHandler = JsonIntegerArrayTypeHandler.class)
    private Integer[] classIndex;

    @ColumnType(column = "during_ids", jdbcType = JdbcType.VARCHAR, typeHandler = JsonIntegerArrayTypeHandler.class)
    private Integer[] duringIds;

    private Boolean checked;

    public AppointmentClassDuring() {}

    public AppointmentClassDuring(Integer id, Integer wxUserId, Integer classId, Integer[] classIndex, Integer[] duringIds, Boolean checked) {
        this.id = id;
        this.wxUserId = wxUserId;
        this.classId = classId;
        this.classIndex = classIndex;
        this.duringIds = duringIds;
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

    public Integer[] getDuringIds() {
        return duringIds;
    }

    public void setDuringIds(Integer[] duringIds) {
        this.duringIds = duringIds;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}

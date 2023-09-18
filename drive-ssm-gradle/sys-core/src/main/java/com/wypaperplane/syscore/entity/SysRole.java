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
import java.util.Arrays;

public class SysRole implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @NotEmpty(message = "name 不能为空字符串")
    @Column(name = "`name`")
    private String name;

    @NotEmpty(message = "encoding 不能为空字符串")
    @Column(name = "encoding")
    private String encoding;

    @NotEmpty(message = "desc 不能为空字符串")
    @Column(name = "`desc`")
    private String desc;

    @NotNull(message = "menuIds 不能为空字符串")
    @ColumnType(column = "menu_ids", jdbcType = JdbcType.VARCHAR, typeHandler = JsonIntegerArrayTypeHandler.class)
    private Integer[] menuIds;

    @NotNull(message = "menuhalfIds 不能为空字符串")
    @ColumnType(column = "menuhalf_ids", jdbcType = JdbcType.VARCHAR, typeHandler = JsonIntegerArrayTypeHandler.class)
    private Integer[] menuhalfIds;

    @NotNull(message = "status 不能为空字符串")
    @Column(name = "`status`")
    private Boolean status;

    @Column(name = "add_time")
    private LocalDateTime addTime;

    public SysRole() {}

    public SysRole(Integer id, String name, String encoding, String desc, @NotNull(message = "menuIds 不能为空字符串") Integer[] menuIds, @NotNull(message = "menuhalfIds 不能为空字符串") Integer[] menuhalfIds, Boolean status, LocalDateTime addTime) {
        this.id = id;
        this.name = name;
        this.encoding = encoding;
        this.desc = desc;
        this.menuIds = menuIds;
        this.menuhalfIds = menuhalfIds;
        this.status = status;
        this.addTime = addTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Integer[] menuIds) {
        this.menuIds = menuIds;
    }

    public Integer[] getMenuhalfIds() {
        return menuhalfIds;
    }

    public void setMenuhalfIds(Integer[] menuhalfIds) {
        this.menuhalfIds = menuhalfIds;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", encoding='" + encoding + '\'' +
                ", desc='" + desc + '\'' +
                ", menuIds=" + Arrays.toString(menuIds) +
                ", menuhalfIds=" + Arrays.toString(menuhalfIds) +
                ", status=" + status +
                ", addTime=" + addTime +
                '}';
    }
}

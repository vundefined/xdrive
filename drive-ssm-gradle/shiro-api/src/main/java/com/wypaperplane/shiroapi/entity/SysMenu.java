package com.wypaperplane.shiroapi.entity;

import com.wypaperplane.syscore.mybatis.JsonStringArrayTypeHandler;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class SysMenu implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @NotNull(message = "pid 为空或类型错误")
    private Integer pid;

    @NotNull(message = "sort 为空或类型错误")
    private Byte sort;

    @NotEmpty(message = "path 不能为空字符串")
    private String path;

    @NotEmpty(message = "url 不能为空字符串")
    private String url;

    @NotEmpty(message = "name 不能为空字符串")
    @Column(name = "`name`")
    private String name;

    @NotEmpty(message = "component 不能为空字符串")
    private String component;

    @NotEmpty(message = "title 不能为空字符串")
    @Column(name = "`title`")
    private String title;

    @NotEmpty(message = "icon 不能为空字符串")
    private String icon;

    @NotNull(message = "type 为空或类型错误")
    private Byte type;

    @NotNull(message = "hidden 为空或类型错误")
    private Boolean hidden;

    @NotNull(message = "keepAlive 为空或类型错误")
    private Boolean keepAlive;

    @NotEmpty(message = "permission 为空或类型错误")
    @ColumnType(column = "`permission`", jdbcType = JdbcType.VARCHAR, typeHandler = JsonStringArrayTypeHandler.class)
    private String[] permission;

    public SysMenu() {}

    public SysMenu(Integer id, Integer pid, Byte sort, String path, String url, String name, String component, String title, String icon, Byte type, Boolean hidden, Boolean keepAlive, String[] permission) {
        this.id = id;
        this.pid = pid;
        this.sort = sort;
        this.path = path;
        this.url = url;
        this.name = name;
        this.component = component;
        this.title = title;
        this.icon = icon;
        this.type = type;
        this.hidden = hidden;
        this.keepAlive = keepAlive;
        this.permission = permission;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String[] getPermission() {
        return permission;
    }

    public void setPermission(String[] permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
                "id=" + id +
                ", pid=" + pid +
                ", sort=" + sort +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", component='" + component + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", hidden=" + hidden +
                ", keepAlive=" + keepAlive +
                ", permission='" + permission + '\'' +
                '}';
    }
}

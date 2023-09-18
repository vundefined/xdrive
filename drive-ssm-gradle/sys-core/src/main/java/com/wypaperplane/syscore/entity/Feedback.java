package com.wypaperplane.syscore.entity;

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
import java.time.LocalDateTime;
import java.util.Arrays;

public class Feedback implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @NotNull(message = "userId 不能为空字符串")
    private Integer wxUserId;

    @NotEmpty(message = "content 不能为空字符串")
    @Column(name = "`content`")
    private String content;

    @ColumnType(column = "`images`", jdbcType = JdbcType.VARCHAR, typeHandler = JsonStringArrayTypeHandler.class)
    private String[] images;

    private LocalDateTime addTime;

    public Feedback() {}

    public Feedback(Integer id, Integer wxUserId, String content, String[] images, LocalDateTime addTime) {
        this.id = id;
        this.wxUserId = wxUserId;
        this.content = content;
        this.images = images;
        this.addTime = addTime;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", wxUserId=" + wxUserId +
                ", content='" + content + '\'' +
                ", images=" + Arrays.toString(images) +
                ", addTime=" + addTime +
                '}';
    }
}

package com.wypaperplane.drivewxmini.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

public class TeacherJob implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @NotNull(message = "userId 不能为空字符串")
    private Integer wxUserId;

    /*
    * 教练从业时间
    * */
    private LocalDate time;

    /*
     * 教练证 证件编号
     * */
    private String encode;

    @NotEmpty(message = "companyName 不能为空字符串")
    private String companyName;

    @NotEmpty(message = "companyAddress 不能为空字符串")
    private String companyAddress;

    /*
     * 教练证 证件图片
     * */
    private String img;

    public TeacherJob() {}

    public TeacherJob(Integer id, Integer wxUserId, LocalDate time, String encode, String companyName, String companyAddress, String img) {
        this.id = id;
        this.wxUserId = wxUserId;
        this.time = time;
        this.encode = encode;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.img = img;
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

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

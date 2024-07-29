package com.wypaperplane.drivewxmini.entity;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

public class DriveLicenseClass implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @Column(name = "`p_id`")
    private Integer pId;

    @Column(name = "`c_name`")
    private String cName;

    @Column(name = "`c_desc`")
    private String cDesc;

    private List<DriveLicenseClass> children;

    public DriveLicenseClass() {}

    public DriveLicenseClass(Integer id, Integer pId, String cName, String cDesc, List<DriveLicenseClass> children) {
        this.id = id;
        this.pId = pId;
        this.cName = cName;
        this.cDesc = cDesc;
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcDesc() {
        return cDesc;
    }

    public void setcDesc(String cDesc) {
        this.cDesc = cDesc;
    }

    public List<DriveLicenseClass> getChildren() {
        return children;
    }

    public void setChildren(List<DriveLicenseClass> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "DriveLicenseClass{" +
                "id=" + id +
                ", pId=" + pId +
                ", cName='" + cName + '\'' +
                ", cDesc='" + cDesc + '\'' +
                ", children=" + children +
                '}';
    }
}

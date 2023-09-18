package com.wypaperplane.syscore.vo;

import com.wypaperplane.syscore.entity.QrRecommend;

import java.math.BigDecimal;

public class QrRecommendVo extends QrRecommend {

    private Integer pId;
    private String cName;
    private String cDesc;

    public QrRecommendVo() {}

    public QrRecommendVo(Integer id, Integer teacherId, Integer classId, Integer price, Integer pId, String cName, String cDesc) {
        super(id, teacherId, classId, price);
        this.pId = pId;
        this.cName = cName;
        this.cDesc = cDesc;
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
}

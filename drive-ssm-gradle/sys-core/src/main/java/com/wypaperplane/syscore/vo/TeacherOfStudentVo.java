package com.wypaperplane.syscore.vo;

import com.wypaperplane.syscore.entity.OrderStudent;
import com.wypaperplane.syscore.entity.TeacherOfStudent;
import com.wypaperplane.syscore.entity.WxUserBase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TeacherOfStudentVo extends TeacherOfStudent {

    private WxUserBase wxUserBase;

    public TeacherOfStudentVo() {}

    public TeacherOfStudentVo(Integer id, Integer teacherId, Integer studentId, Integer recommend, Byte qrType, LocalDateTime addTime, Boolean deleted, WxUserBase wxUserBase) {
        super(id, teacherId, studentId, recommend, qrType, addTime, deleted);
        this.wxUserBase = wxUserBase;
    }

    public WxUserBase getWxUserBase() {
        return wxUserBase;
    }

    public void setWxUserBase(WxUserBase wxUserBase) {
        this.wxUserBase = wxUserBase;
    }
}

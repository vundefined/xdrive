package com.wypaperplane.drivewxmini.vo;

import com.wypaperplane.drivewxmini.entity.TeacherOfStudent;
import com.wypaperplane.drivewxmini.entity.WxUserBase;

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

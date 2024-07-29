package com.wypaperplane.drivewxmini.vo;

import com.wypaperplane.drivewxmini.entity.AppointmentRecord;
import com.wypaperplane.drivewxmini.entity.WxUserBase;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentRecordVo extends AppointmentRecord {

    private WxUserBase wxUserBase;

    public AppointmentRecordVo() {}

    public AppointmentRecordVo(Integer id, Integer studentId, Integer teacherId, Integer classId, Integer[] classIndex, Integer duringId, LocalTime duringStartTime, LocalTime duringEndTime, Byte status, LocalDateTime addTime, WxUserBase wxUserBase) {
        super(id, studentId, teacherId, classId, classIndex, duringId, duringStartTime, duringEndTime, status, addTime);
        this.wxUserBase = wxUserBase;
    }

    public WxUserBase getWxUserBase() {
        return wxUserBase;
    }

    public void setWxUserBase(WxUserBase wxUserBase) {
        this.wxUserBase = wxUserBase;
    }
}

package com.wypaperplane.drivewxmini.vo;

import com.wypaperplane.drivewxmini.entity.AppointmentRecord;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

// 学员提交预约
public class AppointmentStudentVo extends AppointmentRecord {

    private LocalTime appointmentRuleStartTime;

    private LocalTime appointmentRuleEndTime;

    private Integer[] appointmentRuleRest;

    public AppointmentStudentVo() {}

    public AppointmentStudentVo(Integer id, Integer studentId, Integer teacherId, Integer classId, Integer[] classIndex, Integer duringId, LocalTime duringStartTime, LocalTime duringEndTime, Byte status, LocalDateTime addTime, LocalTime appointmentRuleStartTime, LocalTime appointmentRuleEndTime, Integer[] appointmentRuleRest) {
        super(id, studentId, teacherId, classId, classIndex, duringId, duringStartTime, duringEndTime, status, addTime);
        this.appointmentRuleStartTime = appointmentRuleStartTime;
        this.appointmentRuleEndTime = appointmentRuleEndTime;
        this.appointmentRuleRest = appointmentRuleRest;
    }

    public LocalTime getAppointmentRuleStartTime() {
        return appointmentRuleStartTime;
    }

    public void setAppointmentRuleStartTime(LocalTime appointmentRuleStartTime) {
        this.appointmentRuleStartTime = appointmentRuleStartTime;
    }

    public LocalTime getAppointmentRuleEndTime() {
        return appointmentRuleEndTime;
    }

    public void setAppointmentRuleEndTime(LocalTime appointmentRuleEndTime) {
        this.appointmentRuleEndTime = appointmentRuleEndTime;
    }

    public Integer[] getAppointmentRuleRest() {
        return appointmentRuleRest;
    }

    public void setAppointmentRuleRest(Integer[] appointmentRuleRest) {
        this.appointmentRuleRest = appointmentRuleRest;
    }

    @Override
    public String toString() {
        return "StudentAppointmentVo{" +
                "appointmentRuleStartTime=" + appointmentRuleStartTime +
                ", appointmentRuleEndTime=" + appointmentRuleEndTime +
                ", appointmentRuleRest=" + Arrays.toString(appointmentRuleRest) +
                '}';
    }
}

package com.wypaperplane.syscore.enumm;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.HashMap;
import java.util.Map;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AppointmentStatus {
    PENDING(0, "审核中"),
    APPROVED(1, "通过"),
    REJECTED(2, "未通过"),
    REVOCATION(3, "已取消");

    static Map<Integer, AppointmentStatus> enumMap = new HashMap<>();
    static{
        for(AppointmentStatus appointmentStatus : AppointmentStatus.values()){
            enumMap.put(appointmentStatus.getCode(), appointmentStatus);
        }
    }

    private int code;

    private String name;

    private AppointmentStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static AppointmentStatus getEnum(int code) {
        return enumMap.get(code);
    }
}

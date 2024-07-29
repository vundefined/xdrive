package com.wypaperplane.drivewxmini.enumm;

import com.fasterxml.jackson.annotation.JsonFormat;

// @JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AppointmentStatus {
    PENDING(1, "审核中"),
    APPROVED(2, "通过"),
    REJECTED(3, "未通过"),
    REVOCATION(4, "已取消");

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
}

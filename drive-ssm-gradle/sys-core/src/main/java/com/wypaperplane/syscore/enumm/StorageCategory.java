package com.wypaperplane.syscore.enumm;

import java.util.HashMap;
import java.util.Map;

public enum StorageCategory {

    ADMIN_AVATAR(0, "admin_avatar"),
    WX_AVATAR(1, "wx_avatar"),
    TEACHER_JOB_IMG(2, "teacher_job"), // 教练驾驶证
    CAR_IMG(3, "teacher_car"), // 教练上传的机动车图片
    FEEDBACK(4, "feedback"), // 意见反馈
    QRCODE(5, "qrcode");

    static Map<Integer, StorageCategory> enumMap = new HashMap<>();
    static{
        for(StorageCategory storageCategory : StorageCategory.values()){
            enumMap.put(storageCategory.getCode(), storageCategory);
        }
    }

    private int code;

    private String name;

    private StorageCategory(int code, String name) {
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

    public static StorageCategory getEnum(int code) {
        return enumMap.get(code);
    }
}

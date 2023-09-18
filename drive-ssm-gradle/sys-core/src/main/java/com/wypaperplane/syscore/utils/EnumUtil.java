package com.wypaperplane.syscore.utils;

public class EnumUtil {
    public static <T extends EnumCode> String getByCode(Integer code, Class<T> enumClass) {
        for (T item: enumClass.getEnumConstants()) {
            if (code.equals(item.getCode())) {
                return item.getName();
            }
        }
        return null;
    }
}

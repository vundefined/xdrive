package com.wypaperplane.syscore.utils;

import io.micrometer.common.util.StringUtils;

public class RegExpUtil {

    /**
     * 手机号脱敏处理(隐藏中间4位)
     * @param phone 手机号码
     * @return 脱敏后的手机号
     */
    public static String desensitizationPhone(String phone) {
        if (!StringUtils.isBlank(phone)) {
            // 脱敏处理，隐藏手机号中间4位
            phone = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        return phone;
    }

    /**
     * 身份证号脱敏处理(前6位和后4位显示)
     * @param idCard 身份证号
     * @return 脱敏后的身份证号
     */
    public static String desensitizationIdCard(String idCard) {
        return idCard.replaceAll("(?<=\\w{6})\\w(?=\\w{4})", "*");
    }
}

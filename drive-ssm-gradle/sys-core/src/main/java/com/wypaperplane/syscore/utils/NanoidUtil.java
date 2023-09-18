package com.wypaperplane.syscore.utils;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

public class NanoidUtil {

    /*
    * 生成10位纯数字的随机串
    * */
    public static Integer generateNanoidId(int size) {
        char[] alphabet = "0123456789".toCharArray();
        String nanoidId = NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, alphabet, size);
        return Integer.parseInt(nanoidId);
    }
}

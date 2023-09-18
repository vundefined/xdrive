package com.wypaperplane.syscore.utils;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

/*
算法种类                摘要长度
HmacMD5                 128
HmacSHA1                160
HmacSHA256              256
HmacSHA384              384
HmacSHA512              512
*/
public class HmacUtils {

    public static byte[] getHmacKey(String type) {
        try {
            // 1、创建密钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance(type);
            // 2、产生密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 3、获取密钥
            return secretKey.getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encryptHmac(byte[] data, byte[] key, String type) {
        try {
            // 1、还原密钥
            SecretKey secretKey = new SecretKeySpec(key, type);
            // 2、创建MAC对象
            Mac mac = Mac.getInstance(type);
            // 3、设置密钥
            mac.init(secretKey);
            // 4、数据加密
            byte[] bytes = mac.doFinal(data);
            // 5、生成数据
            String rs = encodeHex(bytes, true);
            return rs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 数据转16进制编码
    public static String encodeHex(final byte[] data, final boolean toLowerCase) {
        final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        final char[] toDigits = toLowerCase ? DIGITS_LOWER : DIGITS_UPPER;
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return new String(out);
    }

    /*
    public static void main(String[] args) {
        byte[] data = "java小工匠".getBytes(Charset.forName("UTF-8"));
        // MD5
        byte[] hmacMd5KeyBytes = getHmacKey("HmacMD5");
        String hexHamcMd5Key = encodeHex(hmacMd5KeyBytes, true);
        System.out.println("HMAC Md5 密钥:" + hexHamcMd5Key);
        String hmacMd5Encrypt = encryptHmac(data, hmacMd5KeyBytes, "HmacMD5");
        System.out.println("HMAC Md5 加密:" + hmacMd5Encrypt);
        // SHA256
        byte[] hmacSha256KeyBytes = getHmacKey("HmacSHA256");
        String hexHamcSha256Key = encodeHex(hmacSha256KeyBytes, true);
        System.out.println("HMAC SHA256 密钥:" + hexHamcSha256Key);
        String hmacSha256Encrypt = encryptHmac(data, hmacSha256KeyBytes, "HmacSHA256");
        System.out.println("HMAC SHA256 加密:" + hmacSha256Encrypt);
        // SHA512
        byte[] hmacSha512KeyBytes = getHmacKey("HmacSHA512");
        String hexHamcSha512Key = encodeHex(hmacSha512KeyBytes, true);
        System.out.println("HMAC SHA512 密钥:" + hexHamcSha512Key);
        String hmacSha512Encrypt = encryptHmac(data, hmacSha512KeyBytes, "HmacSHA512");
        System.out.println("HMAC SHA512 加密:" + hmacSha512Encrypt);
    }
     */
}

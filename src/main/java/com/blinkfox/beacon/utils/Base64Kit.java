package com.blinkfox.beacon.utils;

import java.util.Base64;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Base64工具类.
 *
 * @author blinkfox on 2019-04-05.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Base64Kit {

    /**
     * 将字节数组转成 Base64 的字符串.
     *
     * @param bytes 字节数组
     * @return Base64的字符串
     */
    public static String encodeBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 将 Base64 的字符串转成字节数组.
     *
     * @param s Base64的字符串
     * @return 字节数组
     */
    public static byte[] decodeBase64(String s) {
        return Base64.getDecoder().decode(s);
    }

}

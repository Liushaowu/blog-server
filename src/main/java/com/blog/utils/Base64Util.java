package com.blog.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * @Author: tangl
 * @Date: 2019/11/12 12:49
 * @Description:
 */
public class Base64Util {

    /**
     * 加盐
     */
    private static final String SALT_STR = "blog";

    public static String decode(String string) {
        String splitStr = new String(Base64.decodeBase64(string.getBytes()));
        String[] split = splitStr.split(SALT_STR);
        return split[0];
    }
    public static String encode(String string) {
        byte[] bytes = string.concat(SALT_STR).getBytes();
        return new String(Base64.encodeBase64(bytes));
    }
}

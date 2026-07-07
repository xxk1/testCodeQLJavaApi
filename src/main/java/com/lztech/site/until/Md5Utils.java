package com.lztech.site.until;


import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * MD5加密工具类
 */
public class Md5Utils {
    // MD5加码。32位
    public static String md5(String inStr) {
        return DigestUtils.md5DigestAsHex(inStr.getBytes(StandardCharsets.UTF_8)).toLowerCase();
    }

}
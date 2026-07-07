package com.lztech.site.until;


import org.springframework.util.StringUtils;

/**
 * 接口接受参数工具类
 */
public class RequestUtils {

    /**
     * 检查验证码是不是一致
     *
     * @param validKey  服务器将加密的key
     * @param validCode 接受过来的key
     * @return 一致返回true 不一致返回false
     */
    public static boolean checkValidCode(String validKey, String validCode) {
        return !StringUtils.isEmpty(validKey) && !StringUtils.isEmpty(validCode) && Md5Utils.md5(validKey).toLowerCase()
                .equals(validCode.toLowerCase());
    }


}

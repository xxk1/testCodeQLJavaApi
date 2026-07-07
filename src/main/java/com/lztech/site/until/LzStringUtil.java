package com.lztech.site.until;

import org.apache.commons.lang3.StringUtils;

public class LzStringUtil {

    /**
     * 将输入的字符串转为带引号的字符串，如1,2,3转出'1','2','3'
     *
     * @param input
     * @return
     */
    public static String convertToQuotedString(String input) {
        if (StringUtils.isBlank(input)) {
            return "";
        }
        String[] values = input.split(",");
        StringBuilder result = new StringBuilder();
        for (String value : values) {
            if (result.length() > 0) {
                result.append(",");
            }
            result.append("'").append(value).append("'");
        }
        return result.toString();
    }
    /**
     * 将输入的字符串转为带引号的字符串，如1,2,3转出'1','2','3'
     *
     * @param input
     * @return
     */
    public static String convertToQuotedStringBySemicolon(String input) {
        if (StringUtils.isBlank(input)) {
            return "";
        }
        String[] values = input.split(";");
        StringBuilder result = new StringBuilder();
        for (String value : values) {
            if (result.length() > 0) {
                result.append(",");
            }
            result.append("'").append(value).append("'");
        }
        return result.toString();
    }
}

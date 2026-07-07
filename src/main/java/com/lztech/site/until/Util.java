package com.lztech.site.until;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

/**
 * 工具类
 */
public class Util {
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_TIME = "HH:mm:ss";
    public static final String FORMAT_TIME_NO_SECOND = "HH:mm";
    private static final int SCORE_LENGTH = 2, NINEHUNDREDTHOUSAND = 900000, ONEHUNDREDTHOUSAND = 100000;


    private Util() {

    }

    public static SimpleDateFormat getFormat(String format) {
        return new SimpleDateFormat(format);
    }


    /**
     * 时间格式化 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return getFormat(FORMAT_DATE).format(date);
    }

    /**
     * 时间格式化 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return getFormat(FORMAT_DATE_TIME).format(date);
    }

    public static String formatTime(Date date) {
        return getFormat(FORMAT_TIME).format(date);
    }

    public static String formatTimeNoSecond(Date date) {
        return getFormat(FORMAT_TIME_NO_SECOND).format(date);
    }

    public static String formatTimeNoSecond(LocalTime date) {
        return date.format(DateTimeFormatter.ofPattern(FORMAT_TIME_NO_SECOND));
    }

    public static String toChinese(String string) {
        String[] s1 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] s2 = {"十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};
        String result = "";
        int n = string.length();
        for (int i = 0; i < n; i++) {
            int num = string.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - SCORE_LENGTH - i];
            } else {
                result += s1[num];
            }
        }
        if (result.startsWith("一十")) {
            result = result.replaceAll("一十", "十");
        }
        return result;
    }

    public static String nowDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT_DATE));
    }


    public static LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


    public static Boolean betweenStartAndEnd(LocalDate data, LocalDate start, LocalDate end) {
        return (data.isAfter(start) || data.equals(start)) && (data.equals(end) || data.isBefore(end));
    }

    public static synchronized String genUniqueKey() {

        Random random = new Random();

        Integer number = random.nextInt(NINEHUNDREDTHOUSAND) + ONEHUNDREDTHOUSAND;

        return System.currentTimeMillis() + String.valueOf(number);
    }

    public static String initDecimalFormat(double num) {
        BigDecimal bd = new BigDecimal(num);
        return bd.setScale(1, RoundingMode.DOWN).toString();//保留一位数字，并且是截断不进行四舍五;
    }
}
package com.lztech.site.until;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public final static Integer ONE_DAY_MILLISECOND = 1000 * 3600 * 24;
    //yyyy-MM-dd
    public final static String REGEX_DATE_HORIZONTAL_LINE_DIVISION = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|" +
            "[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|" +
            "2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
    //dd/MM/yyyy
    public final static String REGEX_DATE_OBLIQUE_LINE_DIVISION = "(((0[1-9]|[12][0-9]|3[01])/((0[13578]|1[02]))|((0[1-9]|[12][0-9]|" +
            "30)/(0[469]|11))|(0[1-9]|[1][0-9]|2[0-8])/(02))/([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}))|" +
            "(29/02/(([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00)))";
    public final static String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE = "yyyy-MM-dd";

    public final static String MONTH = "MM-dd";
    public final static String TIME = "HH:mm";
    public final static String TIME_SECOND = "HH:mm:ss";
    public final static String DATE_TIME_MINUTES = "yyyy-MM-dd HH:mm";
    public final static String HOUR = "HH";
    public final static String MINUTES = "mm";
    public final static String DATE_HOUR_MINUTES_NOT_SEPARATOR = "yyyyMMddHHmm";

    public static Boolean checkDateStringFormat(String dateString, LineType lineType) {
        Boolean isTrue;
        if (lineType.equals(LineType.HORIZONTAL_LINE)) {
            isTrue = dateString.matches(REGEX_DATE_HORIZONTAL_LINE_DIVISION);
        } else {
            isTrue = dateString.matches(REGEX_DATE_OBLIQUE_LINE_DIVISION);
        }

        return isTrue;
    }

    // 获得某天最大时间 2017-10-15 23:59:59
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    // 获得某天最小时间 2017-10-15 00:00:00
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String formatDate(String pattern, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static Date stringToDate(String pattern, String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        ParsePosition parsePosition = new ParsePosition(0);
        return formatter.parse(dateString, parsePosition);
    }

    public static int daysBetween(Date startDate, Date endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            startDate = sdf.parse(sdf.format(startDate));
            endDate = sdf.parse(sdf.format(endDate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(endDate);
            long time2 = cal.getTimeInMillis();
            long betweenDays = (time2 - time1) / ONE_DAY_MILLISECOND;
            return Integer.parseInt(String.valueOf(betweenDays));
        } catch (ParseException e) {
            return -1;
        }
    }

    public static String addDate(Date date, int calendarType, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(calendarType, amount);
        Date d = calendar.getTime();
        return dateFormat.format(d);
    }

    /**
     * 获取现在时间,这个好用
     *
     * @return返回长时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static Date getSqlDate() {
        return new java.sql.Date(new Date().getTime());
    }

    /**
     * 根据日期获取当天是周几
     *
     * @param date 日期 (格式：yyyy-MM-dd)
     * @return 周几
     */
    public static String dateToWeek(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[w];
    }

    /**
     * @param date    待验证格式时间
     * @param pattern 待验证格式
     * @return true 正确；false：错误
     */
    public static Boolean checkDateTimeFormatter(String date, String pattern) {
        //用于指定 日期/时间 模式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        boolean flag = true;
        try {
            //Java 8 新添API 用于解析日期和时间
            LocalDateTime.parse(date, dateTimeFormatter);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

}

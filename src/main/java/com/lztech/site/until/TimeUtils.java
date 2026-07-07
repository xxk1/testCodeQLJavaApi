package com.lztech.site.until;

import com.lztech.site.constants.Constant;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间工具类
 */
public class TimeUtils {

    /**
     * @return 当前时间 到秒
     */
    public static String getNowDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String getNowDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * @return 当前时间 到秒
     */
    public static String getNowDateMin() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }


    public static Map<Integer, String[]> getTermCalendar(String startDate, long weekCounts) {
        LocalDate parseStartDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate localDate = parseStartDate.with(WeekFields.ISO.dayOfWeek(), 1);
        Map<Integer, String[]> termCalendar = new HashMap<>();
        for (int i = 0; i < weekCounts; i++) {
            if (i != 0) {
                localDate = localDate.plusWeeks(1);
            }
            String[] days = new String[Constant.WEEK_DAYS_NUM];
            LocalDate weekDay = localDate;
            for (int j = 0; j < Constant.WEEK_DAYS_NUM; j++) {
                if (j != 0) {
                    weekDay = weekDay.plusDays(1);
                }
                days[j] = weekDay.format(DateTimeFormatter.ISO_DATE);
            }
            termCalendar.put(i, days);
        }
        return termCalendar;
    }

    public static String getWeekNumByDate(Map<Integer, String[]> termCalendar, String date) {
        for (int i = 0; i < termCalendar.size(); i++) {
            String[] weekDays = termCalendar.get(i);
            for (String weekDay : weekDays) {
                if (weekDay.equals(date)) {
                    return i + 1 + "";
                }
            }
        }
        return null;
    }

    public static long getWeekCounts(String startDate, String endDate) {
        LocalDate startLocalDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate endLocalDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
        //获取周一的日期
        TemporalField fieldISO = WeekFields.ISO.dayOfWeek();
        LocalDate startMonday = startLocalDate.with(fieldISO, 1);
        //获取周日的日期
        LocalDate endSunday = endLocalDate.with(fieldISO, Constant.WEEK_DAYS_NUM);
        long days = endSunday.toEpochDay() - startMonday.toEpochDay() + 1;
        return days / Constant.WEEK_DAYS_NUM;
    }

    public static String dateToStr(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    public static String dateToString(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * date 类转LocalDate 类型
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

}

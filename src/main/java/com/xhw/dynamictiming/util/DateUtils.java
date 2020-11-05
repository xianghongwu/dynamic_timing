package com.xhw.dynamictiming.util;


import org.apache.commons.lang3.time.FastDateFormat;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具
 *
 * @author Somer
 * @date 2018-05-25 15:46
 **/
public class DateUtils {

    public static final Calendar CALENDAR = Calendar.getInstance();

    public static final FastDateFormat FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
    public static final FastDateFormat FORMAT_MM = FastDateFormat.getInstance("yyyy-MM");
    public static final FastDateFormat FORMAT_UN_SIGNED_DATE = FastDateFormat.getInstance("YYYYMMDD");
    public static final FastDateFormat FORMAT_TIME = FastDateFormat.getInstance("hhmmss");
    public static final FastDateFormat FORMAT_TIME_STANDARD = FastDateFormat.getInstance("HH:mm:ss");
    public static final FastDateFormat FORMAT_All = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    public static final String[] WEEK = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 获取当前系统时间
     *
     * @return
     */
    public static Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static Integer getCurrentYear() {
        return CALENDAR.get(Calendar.YEAR);
    }

    /**
     * 获取当前月
     *
     * @return
     */
    public static Integer getCurrentMonth() {
        return CALENDAR.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日
     *
     * @return
     */
    public static Integer getCurrentDay() {
        return CALENDAR.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前是星期几
     *
     * @return
     */
    public static String getCurrentWeek() {
        CALENDAR.setTime(new Date());
        int w = CALENDAR.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return WEEK[w];
    }

    /**
     * 根据日期获取对应的星期数
     *
     * @param time
     * @return
     */
    public static Integer getWeekNumberByDate(String time) {
        Integer week = 0;
        try {
            CALENDAR.setTime(FORMAT.parse(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = CALENDAR.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            week = 7;
        } else {
            week = w;
        }
        return week;
    }

    /**
     * 根据日期获取对应的星期数
     *
     * @return
     */
    public static Integer getWeekNumberByDate() {
        Integer week = 0;
        Calendar calendar =  Calendar.getInstance();
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            week = 7;
        } else {
            week = w;
        }
        return week;
    }

    /**
     * 根据日期获取星期数
     *
     * @param time
     * @return
     */
    public static String getWeekByDate(String time) {
        try {
            CALENDAR.setTime(FORMAT.parse(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = CALENDAR.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return WEEK[w];
    }

    /**
     * 根据日期字符串转换为 Date时间
     *
     * @param time
     * @return
     */
    public static Date getDateByTime(String time) {
        Date date = null;
        try {
            date = FORMAT_All.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 根据日期字符串截取年份
     *
     * @param time
     * @return
     */
    public static Integer getYearByTime(String time) {
        if (null == time) {
            return null;
        }
        String year = time.substring(0, 4);
        return Integer.valueOf(year);
    }

    /**
     * 根据日期字符串截取月份
     *
     * @param time 日期格式：YYYY-MM-dd
     * @return
     */
    public static Integer getMonthByTime(String time) {
        if (null == time) {
            return null;
        }
        String month = null;
        String[] timeArray = time.split("-");
        month = timeArray[1];
        return Integer.valueOf(month);
    }

    /**
     * 根据日期字符串截取月份
     *
     * @param time 日期格式：YYYY-MM-dd
     * @return
     */
    public static String getCompleteMonthByTime(String time) {
        if (time == null) {
            return null;
        }
        String month;
        String[] timeArray = time.split("-");
        month = timeArray[1];
        return month;
    }

    /**
     * 根据日期字符串截取天数
     *
     * @param
     * @return
     */
    public static Integer getDayByTime(String time) {
        if (null == time) {
            return null;
        }
        String day = null;
        String[] timeArray = time.split("-");
        day = timeArray[2];
        return Integer.valueOf(day);
    }

    /**
     * 根据日期字符串去掉毫秒
     *
     * @param time
     * @return
     */
    public static String getResultDate(String time) {
        if (time == null) {
            time = "";
        }
        if (time.indexOf(".") != -1) {
            time = time.substring(0, time.indexOf("."));
        }
        return time;
    }

    public static String getStrDate(Date date) {
        return FORMAT_All.format(date);
    }

    /**
     * Date转String
     *
     * @return
     */
    public static String getCurrentDate() {
        return getCurrentTime().toString().substring(0, 10);
    }

    /**
     * 获取指定天数之前日期
     *
     * @param days
     * @return
     */
    public static String getBeforeDate(int days) {
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(new Date());
        theCa.add(theCa.DATE, -days);
        Date start = theCa.getTime();
        String beforeDate = FORMAT_All.format(start);
        return beforeDate;
    }

    public static String getCurrentDateTime() {
        return FORMAT_All.format(new Date());
    }

    public static void main(String[] args) throws Exception {
        /*Date startTime = FORMAT_TIME.parse("163920");
        Date finishTime = FORMAT_TIME.parse("125645");
        Date startDate = FORMAT_UN_SIGNED_DATE.parse("20181115");
        Date finishDate = FORMAT_UN_SIGNED_DATE.parse("20181120");
        System.out.println(startTime.getTime() > finishTime.getTime() ? true : false);
        System.out.println(startDate.getTime() > finishDate.getTime() ? true : false);
        System.out.println("当前日期："+getCurrentDate().replaceAll("-",""));*/
        System.out.println(getBeforeDate(30));
        System.out.println(getCurrentDateTime());
    }

    /**
     * 根据日期获取对应的星期数
     *
     * @return
     */
    public static Integer getCurrentWeekNumber() {
        Calendar CALENDAR = Calendar.getInstance();
        Integer week = 0;
        try {
            CALENDAR.setTime(getCurrentTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = CALENDAR.get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0) {
            week = 7;
        } else {
            week = w;
        }
        return week;
    }


    /**
     * 根据日期获取对应的星期数
     *
     * @return
     */
    public static Integer getCurrentWeekNumber(String ymd) {
        Calendar CALENDAR = Calendar.getInstance();
        Integer week = 0;
        try {
            CALENDAR.setTime(FORMAT.parse(ymd));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = CALENDAR.get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0) {
            week = 7;
        } else {
            week = w;
        }
        return week;
    }
}
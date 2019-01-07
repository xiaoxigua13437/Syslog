package com.telin.syslog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhaofang on 2019/1/7.
 * 功能说明:日期工具类
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static String FORMAT_SHORT = "yyyy-MM-dd";

    public static String FORMAT_SHORT_WITH_MONTH = "yyyy-MM";

    public static String FORMAT_SHORT_WITH_MONTH_NO_MID_LINE = "yyyyMM";

    public static String FORMAT_SHORT_WITH_NO_MID_LINE = "yyyyMMdd";

    public static String FORMAT_SHORT_WITH_HOUR_NO_MID_LINE = "yyyyMMddHH";

    public static String FORMAT_SHORT_WITH_MIN_NO_MID_LINE = "yyyyMMddHHmm";

    public static String FORMAT_LONG_WITH_NO_MID_LINE = "yyyyMMddHHmmss";

    public static String FORMAT_LONG_WITH_MILL_NO_MID_LINE = "yyyyMMddHHmmssSSS";

    public static String FORMAT_SHORT_WITH_HOUR = "yyyy-MM-dd HH";

    public static String FORMAT_SHORT_WITH_MINUTE = "yyyy-MM-dd HH:mm";

    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    public static String FORMAT_DEFAULT = FORMAT_LONG;

    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.SSS";

    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    public static String getDefaultDatePattern()
    {
        return FORMAT_DEFAULT;
    }

    public static String getNowString()
    {
        return format(new Date());
    }

    public static String getNowString(String format)
    {
        return format(new Date(), format);
    }

    public static String format(Date date)
    {
        return format(date, getDefaultDatePattern());
    }

    public static String format(Date date, String pattern)
    {
        String returnValue = null;
        if ((date == null) || (pattern == null))
            logger.error("Date or Pattern is null,Date:" + date + ";Pattern:" + pattern);
        else {
            try {
                SimpleDateFormat df = new SimpleDateFormat(pattern);
                returnValue = df.format(date);
            } catch (Exception e) {
                logger.error("日期格式转换成字符串的时候出错。Date:" + date + ";Pattern:" + pattern, e);
            }
        }
        return returnValue;
    }

    public static Date parse(String strDate)
    {
        return parse(strDate, getDefaultDatePattern());
    }

    public static Date parse(String strDate, String pattern)
    {
        Date date = null;
        if ((strDate == null) || ("".equals(strDate)) || (pattern == null))
            logger.error("strDate or Pattern is null,strDate:" + strDate + ";Pattern:" + pattern);
        else {
            try {
                SimpleDateFormat df = new SimpleDateFormat(pattern);
                date = df.parse(strDate);
            } catch (ParseException e) {
                logger.error("字符串转换成日期格式的时候出错。strDate:" + strDate + ";Pattern:" + pattern, e);
            }
        }
        return date;
    }

    public static Date addMonth(Date date, int n)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(2, n);
        return cal.getTime();
    }

    public static Date addDay(Date date, int n)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, n);
        return cal.getTime();
    }

    public static String getpreHour(String format, int h)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        date.setTime(date.getTime() + h * 60 * 60 * 1000);
        return sdf.format(date);
    }

    public static String getTimeString()
    {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_DEFAULT);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    public static String getTimeFullString()
    {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    public static String getYear(Date date)
    {
        return format(date).substring(0, 4);
    }

    public static int getMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(2) + 1;
    }

    public static int getDay(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(5);
    }

    public static int getHour(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(11);
    }

    public static int getMinute(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(12);
    }

    public static int getSecond(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(13);
    }

    public static long getMillis(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    public static int countDays(String date)
    {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int)(t / 1000L - t1 / 1000L) / 3600 / 24;
    }

    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000L - t1 / 1000L) / 3600 / 24;
    }



}

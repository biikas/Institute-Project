package com.f1soft.campaign.common.util;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DateHelper {

    private DateHelper() {
    }

    public static long getSecondsDifference(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        long min = diff / 1000;
        return min;
    }

    public static boolean isExpiredInDays(int interval, Date date) {
        Date expireDate = addDay(date, interval);
        return expireDate.before(new Date());
    }

    public static boolean isExpiredInMinutes(int interval, Date date) {
        Date expireDate = addMinutes(date, interval);
        return expireDate.before(new Date());
    }

    public static boolean isBankAccountExpired(int interval, Date date) {
        Date expireDate = addMonths(date, interval);
        return expireDate.before(new Date());
    }

    public static boolean isValidRange(String strFromDate, String strToDate) {

        Date fromDate = DateFormatter.convertToDate(strFromDate);
        Date toDate = DateFormatter.convertToDate(strToDate);
        return fromDate.before(toDate);
    }

    public static boolean matchDate(LocalDate date1, LocalDate date2) {
        return date1.equals(date2);
    }

    public static Date subtractMonth(Date date, Integer month) {
        return Date.from(ZonedDateTime.now().minusMonths(month).toInstant());
    }

    public static Date subtractDays(Date date, Integer days) {
        return Date.from(ZonedDateTime.now().minusDays(days).toInstant());
    }

    public static long getMinutesDifference(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        long min = diff / (60 * 1000);
        return min;
    }

    public static Date addDay(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static Date addSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    public static Date addMinutes(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    public static Date addHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hours);
        return calendar.getTime();
    }

    public static Date addMonths(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    public static DateTime convertToDate(String date, String format) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
        return fmt.parseDateTime(date);
    }

    public static String convertToString(DateTime dt, String format) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
        return fmt.print(dt);
    }

    public static boolean checkBetweenDate(Date startDate, Date dateToCheck, Date endDate) {
        DateTime d1 = new DateTime(startDate);
        DateTime d2 = new DateTime(dateToCheck);
        DateTime d3 = new DateTime(endDate);

        Interval interval = new Interval(d1, d3);
        boolean intervalContainsDateTime2 = interval.contains(d2);
        return intervalContainsDateTime2;
    }

    public static DateTime addSeconds(DateTime dt, int no) {
        return dt.plus(Period.seconds(no));
    }

    public static DateTime addMinutes(DateTime dt, int no) {
        return dt.plus(Period.minutes(no));
    }

    public static long daysDifference(Date date1, Date date2) {
        long difference = date2.getTime() - date1.getTime();
        return TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
    }

    public static Date getNextSchedulePaymentDate(Date today, String time) {
        String[] split = time.split(":");
        int hr = Integer.parseInt(split[0]);
        int min = Integer.parseInt(split[1]);

        today.setDate(today.getDate());
        today.setHours(hr);
        today.setMinutes(min);
        today.setSeconds(0);
        return today;
    }

    public static boolean checkTime(String startTime, String endTime) {
        try {
            Date time1 = new SimpleDateFormat("HH:mm").parse(startTime);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);
            Date time2 = new SimpleDateFormat("HH:mm").parse(endTime);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            Date time3 = new SimpleDateFormat("HH:mm").parse(getDateTimeWithMiliSecondsToString(new Date()));
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(time3);
            Date x = calendar3.getTime();
            log.info("start: {}, end: {}, current: {}", calendar1.getTime(), calendar2.getTime(), calendar3.getTime());
            return x.after(calendar1.getTime()) && x.before(calendar2.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkDateTime(Date startTime, Date endTime) {
        try {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(startTime);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(endTime);
            Date time3 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(getDateTimeString(new Date()));
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(time3);
            Date x = calendar3.getTime();
            log.info("start: {}, end: {}, current: {}", calendar1.getTime(), calendar2.getTime(), calendar3.getTime());
            return x.after(calendar1.getTime()) && x.before(calendar2.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getDateTimeString(Date date) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            String strDate = dateformat.format(date);
            return strDate;
        } catch (Exception ex) {
            return null;
        }
    }

    public static String getDateTimeWithMiliSecondsToString(Date date) {
        SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm");
        try {
            String strDate = dateformat.format(date);
            return strDate;
        } catch (Exception ex) {
            return null;
        }
    }

    public static boolean isCurrentDate(Date checkDate) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(checkDate).equals(fmt.format(new Date()));
    }
}

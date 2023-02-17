package com.f1soft.campaign.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

@Slf4j
public class DateFormatter extends DateFormat {

    private DateFormatter() {
    }

    public static String convertToString(Date date) {
        return date != null ? new SimpleDateFormat(DATE_FORMAT).format(date) : "";
    }

    public static String convertToString(String date, String dateFormat) {
        try {
            return date != null ? String.valueOf(new SimpleDateFormat(dateFormat).parse(date)) : "";
        } catch (ParseException e) {
            log.error("Error : {}", e.getMessage());
            return "";
        }
    }

    public static Date convertToDate(String text) {
        if (text == null) {
            return null;
        } else {
            String str = text.trim();
            try {
                return DateUtils.parseDate(str, DATE_PATTERNS);
            } catch (ParseException e) {
                log.error("Exception : {}", e);
                return null;
            }
        }
    }

    public static DateTime convertToDate(String date, String format) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
        return fmt.parseDateTime(date);
    }

    public static boolean checkBetweenDate(Date startDate, Date dateToCheck, Date endDate) {

        DateTime d1 = new DateTime(startDate);
        DateTime d2 = new DateTime(dateToCheck);
        DateTime d3 = new DateTime(endDate);

        Interval interval = new Interval(d1, d3);
        boolean intervalContainsDateTime2 = interval.contains(d2);
        return intervalContainsDateTime2;
    }

    public static String changeFormat(Date date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date changeToStartDate(Date date) {
        try {
            return DateBuilder.date(date).atHourOfDay(00).atMinute(00).atSecond(00).build();
        } catch (Exception e) {
            return null;
        }
    }

    public static Date changeToEndDate(Date date) {
        try {
            return DateBuilder.date(date).atHourOfDay(23).atMinute(59).atSecond(59).build();
        } catch (Exception e) {
            return null;
        }
    }

    public static String convertToString(Date date, String dateFormat) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String changeFormat(Date date, String dateFormat) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getDateFromString(String from) {
        java.text.DateFormat df = new SimpleDateFormat();
        if (from.contains("-")) {
            df = new SimpleDateFormat("yyyy-MM-dd");
        } else if (from.contains("/")) {
            df = new SimpleDateFormat("yyyy/MM/dd");
        } else if (from.contains("\\")) {
            df = new SimpleDateFormat("yyyy\\MM\\dd");
        }
        Date today;
        try {
            today = df.parse(from);
            today.setDate(today.getDate());
            today.setHours(0);
            today.setMinutes(0);
            today.setSeconds(0);
        } catch (ParseException e) {
            return null;
        }
        return today;
    }

    public static String txnHistoryFormat(String date) {
        try {
            Date formattedDate = convertToDate(date);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat.TXN_HISTORY_FORMAT);
            return simpleDateFormat.format(formattedDate);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getDayMonthYear(String date){
        LocalDate currentDate
                = LocalDate.parse(date);

        // Get day from date
        int day = currentDate.getDayOfMonth();

        // Get month from date
        Month month = currentDate.getMonth();

        // Get year from date
        int year = currentDate.getYear();

        return day +" "+month+" "+year;
    }

}

package com.f1soft.campaign.common.util;

import java.beans.PropertyEditorSupport;

public class DateFormat extends PropertyEditorSupport {

    public static final String  DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_ONLY_FORMAT = "yyyy-MM-dd";
    public final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_TIME_ZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public final static String START_DATE_FORMAT = "yyyy-MM-dd 00:00:00";
    public final static String SMS_DATE_FORMAT = "dd/MM/yyyy";
    public final static String STATEMENT_TXN_DAY = "dd-MM-yyyy";
    public static final String SQL_DATE_FORMAT = "yyyy-MM-dd";
    public static final String CBS_DATE_FORMAT = "dd-MMMM-yyyy";
    public static final String INVOICE_DATE_FORMAT = "dd MMM yyyy,hh:mm a";

    public static final String YEAR_MONTH_DAY_HR_MM_SS = "MM/dd/yyyy HH:mm:ss";
    public static final String DAY_MONTH_YEAR_HR_MM_SS = "dd-MM-YYYY HH:mm:ss";
    public static final String YEAR_MONTH_DAY = "yyyy/MM/dd";
    public static final String WALLET_TXN_HISTORY_FORMAT = "dd/MM/yyyy";
    public static final String TXN_HISTORY_FORMAT = "MMM dd, yyyy 'at' hh:mm a";

    public static final String[] DATE_PATTERNS = {"yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd",
            "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss", "dd MMM yyyy,hh:mm a"};

    public enum IntervalUnit {MILLISECOND, SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR}

    public static final int SUNDAY = 1;

    public static final int MONDAY = 2;

    public static final int TUESDAY = 3;

    public static final int WEDNESDAY = 4;

    public static final int THURSDAY = 5;

    public static final int FRIDAY = 6;

    public static final int SATURDAY = 7;

    public static final int JANUARY = 1;

    public static final int FEBRUARY = 2;

    public static final int MARCH = 3;

    public static final int APRIL = 4;

    public static final int MAY = 5;

    public static final int JUNE = 6;

    public static final int JULY = 7;

    public static final int AUGUST = 8;

    public static final int SEPTEMBER = 9;

    public static final int OCTOBER = 10;

    public static final int NOVEMBER = 11;

    public static final int DECEMBER = 12;

    public static final long MILLISECONDS_IN_MINUTE = 60l * 1000l;

    public static final long MILLISECONDS_IN_HOUR = 60l * 60l * 1000l;

    public static final long SECONDS_IN_MOST_DAYS = 24l * 60l * 60L;

    public static final long MILLISECONDS_IN_DAY = SECONDS_IN_MOST_DAYS * 1000l;
}

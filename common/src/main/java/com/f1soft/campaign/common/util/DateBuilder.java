package com.f1soft.campaign.common.util;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.Calendar;
import java.util.Date;

public class DateBuilder extends DateFormat {
    private int month;
    private int day;
    private int year;
    private int hour;
    private int minute;
    private int second;
    private Date date;

    /**
     * Create a DateBuilder, with initial settings for the current date and time in the system default timezone.
     */
    private DateBuilder() {
        Calendar cal = Calendar.getInstance();

        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        year = cal.get(Calendar.YEAR);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        second = cal.get(Calendar.SECOND);
    }

    private DateBuilder(Date date) {
        this.date = date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.date);

        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        year = cal.get(Calendar.YEAR);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        second = cal.get(Calendar.SECOND);
    }

    public static DateBuilder newDate() {
        return new DateBuilder();
    }

    public static DateBuilder date(Date date) {
        return new DateBuilder(date);
    }

    public static DateBuilder firstDayOfMonth(Date date) {
        return new DateBuilder(date).inDay(1);
    }

    /**
     * Set the hour (0-23) for the Date that will be built by this builder.
     */
    public DateBuilder atHourOfDay(int atHour) {
        validateHour(atHour);

        this.hour = atHour;
        return this;
    }

    /**
     * Set the minute (0-59) for the Date that will be built by this builder.
     */
    public DateBuilder atMinute(int atMinute) {
        validateMinute(atMinute);

        this.minute = atMinute;
        return this;
    }

    /**
     * Set the second (0-59) for the Date that will be built by this builder, and truncate the milliseconds to 000.
     */
    public DateBuilder atSecond(int atSecond) {
        validateSecond(atSecond);

        this.second = atSecond;
        return this;
    }

    public DateBuilder atHourMinuteAndSecond(int atHour, int atMinute, int atSecond) {
        validateHour(atHour);
        validateMinute(atMinute);
        validateSecond(atSecond);

        this.hour = atHour;
        this.second = atSecond;
        this.minute = atMinute;
        return this;
    }

    /**
     * Set the day of month (1-31) for the Date that will be built by this builder.
     */
    public DateBuilder onDay(int onDay) {
        validateDayOfMonth(onDay);

        this.day = onDay;
        return this;
    }

    /**
     * Set the month (1-12) for the Date that will be built by this builder.
     */
    public DateBuilder inMonth(int inMonth) {
        validateMonth(inMonth);

        this.month = inMonth;
        return this;
    }

    public DateBuilder inDay(int inDay) {
        validateDayOfMonth(inDay);

        this.day = inDay;
        return this;
    }

    public DateBuilder inMonthOnDay(int inMonth, int onDay) {
        validateMonth(inMonth);
        validateDayOfMonth(onDay);

        this.month = inMonth;
        this.day = onDay;
        return this;
    }

    /**
     * Set the year for the Date that will be built by this builder.
     */
    public DateBuilder inYear(int inYear) {
        validateYear(inYear);

        this.year = inYear;
        return this;
    }

    public Date build() {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    private static int translate(IntervalUnit unit) {
        switch (unit) {
            case DAY:
                return Calendar.DAY_OF_YEAR;
            case HOUR:
                return Calendar.HOUR_OF_DAY;
            case MINUTE:
                return Calendar.MINUTE;
            case MONTH:
                return Calendar.MONTH;
            case SECOND:
                return Calendar.SECOND;
            case MILLISECOND:
                return Calendar.MILLISECOND;
            case WEEK:
                return Calendar.WEEK_OF_YEAR;
            case YEAR:
                return Calendar.YEAR;
            default:
                throw new IllegalArgumentException("Unknown IntervalUnit");
        }
    }

    public static void validateDayOfWeek(int dayOfWeek) {
        if (dayOfWeek < SUNDAY || dayOfWeek > SATURDAY) {
            throw new IllegalArgumentException("Invalid day of week.");
        }
    }

    public static void validateHour(int hour) {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException(
                    "Invalid hour (must be >= 0 and <= 23).");
        }
    }

    public static void validateMinute(int minute) {
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException(
                    "Invalid minute (must be >= 0 and <= 59).");
        }
    }

    public static void validateSecond(int second) {
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException(
                    "Invalid second (must be >= 0 and <= 59).");
        }
    }

    public static void validateDayOfMonth(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid day of month.");
        }
    }

    public static void validateMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(
                    "Invalid month (must be >= 1 and <= 12.");
        }
    }

    private static final int MAX_YEAR = Calendar.getInstance().get(Calendar.YEAR) + 100;

    public static void validateYear(int year) {
        if (year < 0 || year > MAX_YEAR) {
            throw new IllegalArgumentException(
                    "Invalid year (must be >= 0 and <= " + MAX_YEAR);
        }
    }

    public static DateTime addMonths(DateTime dt, int no) {
        return dt.plus(Period.months(no));
    }

}

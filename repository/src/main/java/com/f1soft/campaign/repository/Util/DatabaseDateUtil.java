package com.f1soft.campaign.repository.Util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @Author Rashim Dhaubanjar
 */
@Slf4j
public class DatabaseDateUtil {

    public static final String[] DATE_PATTERNS = {"yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd",
            "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss", "dd MMM yyyy,hh:mm a"};

    public static Date convertToDate(String text) {
        if (text == null) {
            return null;
        } else {
            String str = text.trim();
            try {
                return DateUtils.parseDate(str, DATE_PATTERNS);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    public static String formatFromDate(Date date, String databaseVendor) {
        String dateFormat;
        if (databaseVendor.equalsIgnoreCase(DBConstant.MSSQL_VENDOR)) {
            dateFormat = "MM/dd/yy 00:00:00.000";
        } else if (databaseVendor.equalsIgnoreCase(DBConstant.MYSQL_VENDOR)) {
            dateFormat = "yyyy-MM-dd 00:00:00";
        } else {
            dateFormat = "yyyy-MM-dd";
        }
        return new SimpleDateFormat(dateFormat).format(date);
    }

    public static String formatToDate(Date date, String databaseVendor) {
        String dateFormat;
        if (databaseVendor.equalsIgnoreCase(DBConstant.MSSQL_VENDOR)) {
            dateFormat = "MM/dd/yy 23:59:59.999";
        } else if (databaseVendor.equalsIgnoreCase(DBConstant.MYSQL_VENDOR)) {
            dateFormat = "yyyy-MM-dd 23:59:59";
        } else {
            dateFormat = "yyyy-MM-dd";
        }
        return new SimpleDateFormat(dateFormat).format(date);
    }
}

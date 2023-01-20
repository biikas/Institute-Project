package com.nikosera.repository.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Sauravi Thapa ON 2/23/21
 */
public class DateUtils {

    public static java.sql.Date utilDateToSqlDate(Date uDate) {
        try {
            DateFormat sqlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            return java.sql.Date.valueOf(sqlDateFormatter.format(uDate));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Long getTimeInMillisecondsFromLocalDate() {
        LocalDateTime localDate = LocalDateTime.now();
        return Timestamp.valueOf(localDate).getTime();
    }
}

package com.f1soft.campaign.repository.Util;

/**
 * @author Prajwol hada
 */
public class QueryFormatter {

    public static String escapeString(String str) {
        if (str != null && str.length() > 0) {
            str = str.replace("\\", "")
                    .replace("'", "")
                    .replace("\n", "")
                    .replace("\r", "")
                    .replace("\"", "");
        }
        return str;
    }
}

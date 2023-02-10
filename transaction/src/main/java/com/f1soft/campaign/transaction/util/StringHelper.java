package com.f1soft.campaign.transaction.util;

import java.util.ArrayList;
import java.util.List;

public class StringHelper {

    public static List<String> stringToList(String formattedValue, String separator) {
        String[] arrStr = formattedValue.split(separator);
        List<String> stringList = new ArrayList<>();
        for (String value : arrStr) {
            stringList.add(value);
        }
        return stringList;
    }

    public static String convertToEmptyIfNull(String data) {
        return data != null ? data : "";
    }

    public static String trimTrailingCharacter(String text, int length) {
        String formattedText = text;
        if (text.length() > length) {
            formattedText = formattedText.substring(0, length);
        }
        return formattedText;
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}

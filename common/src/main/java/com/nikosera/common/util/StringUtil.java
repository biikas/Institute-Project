package com.nikosera.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.join;

/**
 * @author Sauravi Thapa ON 2/24/21
 */

public class StringUtil {

    public static String splitByCharacterTypeCamelCase(String name) {
        return join(StringUtils.splitByCharacterTypeCamelCase
                (name.replaceAll("\\d+", "")), " ");
    }

    public static String toNormalCase(String text) {

        final char[] delimiters = {' ', '_'};

        return WordUtils.capitalizeFully(text, delimiters);
    }

    public static String toUpperCase(String name) {
        return name.toUpperCase();
    }

    public static String convertListToCommaSeparatedString(List<Long> ids) {
        return join(ids,",");
    }
}

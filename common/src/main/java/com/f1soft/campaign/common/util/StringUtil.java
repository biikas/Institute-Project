package com.f1soft.campaign.common.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <krishna.pandey@f1soft.com>
 */

@Slf4j
public class StringUtil {

    public static boolean validateNullOrEmpty(String text) {

        return text == null || text.trim().length() == 0;
    }
}

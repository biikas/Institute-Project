package com.f1soft.campaign.common.util;


import java.util.concurrent.TimeUnit;

/**
 * @author Rashim Dhaubanjar
 */

public class ThreadUtil {

    public static void sleepForSecond(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ire) {
        }
    }

    public static void sleepForMilliSecond(int milliSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliSeconds);
        } catch (InterruptedException ire) {
        }
    }
}

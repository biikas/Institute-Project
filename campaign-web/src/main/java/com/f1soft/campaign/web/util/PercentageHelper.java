package com.f1soft.campaign.web.util;

/**
 * @author Shreetika Panta
 */
public class PercentageHelper {

    public static double convertToRemainingPercentage(Double spentLimit, Double totalLimit) {
        Double percent = (spentLimit / totalLimit) * 100;
        return Double.parseDouble(String.format("%.2f", percent));
    }


}



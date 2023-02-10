package com.f1soft.campaign.transaction.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class AmountFormatter {
    public static final String DECIMAL_POINT_FORMAT = "##,##,##,##,##,##,##0.00";
    public static final String AMOUNT_FORMAT = "0.##";

    public static String format(Double amount, String format) {
        DecimalFormat df = new DecimalFormat(format);
        String formatedAmount = df.format(amount);
        return formatedAmount;
    }

    public static String decimalFormat(Double amount) {
        DecimalFormat df = new DecimalFormat(DECIMAL_POINT_FORMAT);
        String formatedAmount = df.format(amount);
        return formatedAmount;
    }

    public static String amountFormat(Double amount) {
        DecimalFormat df = new DecimalFormat(AMOUNT_FORMAT);
        String formatedAmount = df.format(amount);
        return formatedAmount;
    }

    public static String format(double amount) {
        if (amount < 1000) {
            DecimalFormat df = new DecimalFormat("###.00");
            return df.format(amount);
        } else {
            DecimalFormat df1 = new DecimalFormat(",##");
            DecimalFormat df2 = new DecimalFormat("000.00");
            double hundreds = amount % 1000;
            int other = (int) (amount / 1000);
            return df1.format(other) + ',' + df2.format(hundreds);
        }
    }

    public static BigDecimal doubleToBigDecimal(Double amount) {
        return new BigDecimal(String.valueOf(amount));
    }
}

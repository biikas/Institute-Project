package com.f1soft.campaign.common.util;

import java.util.Random;
import java.util.UUID;

@SuppressWarnings({"Duplicates"})
public class RandomGenerator {

    final static int[] sizeTable = {0, 9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE};
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz"
            + "/=+";
    private static final String CAPS_ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789";
    private static final String NUM = "0123456789";
    private static final Random random = new Random();

    public static int generate(int digit) {
        int highest = sizeTable[digit] + 1;
        int lowest = sizeTable[digit - 1] + 1;

        int generated = random.nextInt(highest);

        if (generated < lowest) {
            generated = generate(digit);
        }

        return generated;
    }

    public static String generateRandomNumber(int alphaLenth, int numericLength) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < alphaLenth; i++) {
            int ndx = (int) (Math.random() * ALPHA.length());
            sb.append(ALPHA.charAt(ndx));
        }
        for (int i = 0; i < numericLength; i++) {
            int ndx = (int) (Math.random() * NUM.length());
            sb.append(NUM.charAt(ndx));
        }
        return sb.toString();
    }

    public static String generateAlphaNumeric(int alphaLenth, int numericLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < alphaLenth; i++) {
            int ndx = (int) (Math.random() * ALPHA.length());
            sb.append(ALPHA.charAt(ndx));
        }
        for (int i = 0; i < numericLength; i++) {
            int ndx = (int) (Math.random() * NUM.length());
            sb.append(NUM.charAt(ndx));
        }
        return sb.toString();
    }

    public static String generateAlphaNumeric(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    public static String generateRandomAlphaNumeric(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int ndx = (int) (Math.random() * ALPHA_NUMERIC.length());
            sb.append(ALPHA_NUMERIC.charAt(ndx));
        }
        return sb.toString();
    }

    public static String generateRandomCapsAlphaNumeric(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int ndx = (int) (Math.random() * CAPS_ALPHA_NUMERIC.length());
            sb.append(CAPS_ALPHA_NUMERIC.charAt(ndx));
        }
        return sb.toString();
    }

    public static String generateRandomCapsAlphaNumeric(String prefix, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int ndx = (int) (Math.random() * CAPS_ALPHA_NUMERIC.length());
            sb.append(CAPS_ALPHA_NUMERIC.charAt(ndx));
        }
        return prefix + sb;
    }

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateRandomAlphaNumeric(7));
    }
}

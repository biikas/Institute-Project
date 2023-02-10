package com.f1soft.campaign.common.helper;

public class IdHelper {

    public static String getStringValue(Long id) {
        // TODO: encryption logic to be implemented here
        return String.valueOf(id);
    }

    public static Long getLongValue(String eid) {
        // TODO:decryption logic to be implemented here
        return Long.valueOf(eid);
    }
}

package com.f1soft.campaign.common.pki;

import java.util.Base64;

/**
 * @author Manjit Shakya <mnzitshakya@gmail.com>
 */
public class Base64Util {

    public static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

}

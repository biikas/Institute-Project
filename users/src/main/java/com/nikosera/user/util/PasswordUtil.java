package com.nikosera.user.util;

import com.mifmif.common.regex.Generex;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
public class PasswordUtil {

    public static String getRandomPassword() {
        Generex generex = new Generex("[a-zA-Z]{5,}[0-9]{1,}$");
        return generex.random();
    }

}

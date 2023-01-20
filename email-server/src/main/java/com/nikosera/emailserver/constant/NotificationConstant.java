package com.nikosera.emailserver.constant;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */

public interface NotificationConstant {

    String NOTIFIER = "system@admin.com";

    interface Status {
        String PENDING = "Pending";
        String SENT = "Sent";
    }

    interface Type {
        String EMAIL = "Email";
        String SMS = "Sms";
    }

    interface Subject {
        String USER_REGISTRATION = "User registration";
        String CHANGE_PASSWORD = "Change Password";
    }

    interface Template {
        String USER_REGISTRATION = "UserRegister";
        String CHANGE_PASSWORD = "ChangePassword";
    }
}

package com.nikosera.common.constant;

public interface ApiConstant {

    interface Auth {
        String LOGIN = "login";
        String VALIDATE_TOTP = "/validate/otp";

    }

    String ECHO = "echo";

    interface Web {
        String USER = "user";
        String  WRAP_USER_ID = "{userId}";
        String USER_ID = "userId";
        String LOGGEDIN_USER = "loggedin-user";
        String PAGED = "paged";
        String CREATE = "save";
        String USER_GROUP = "user-group";
        String WRAP_GROUP_ID = "{groupId}";
        String GROUP_ID = "groupId";
        String MANAGE = "manage";

        String CHANGE_PASSWORD = "change-password";


        String SEARCH = "search";

        String CHANGE_IMAGE = "change-image";


        String QR = "qr/data";
        String CHANGE_OTP = "change-otp";
        String UPDATE_PROFILE = "update-profile";

        String DASHBOARDS = "dashboards";
        String MENUS = "menus";


    }

    String GET_ALL = "get-all";
    String GET_BY_ID = "get-by-id";


    static String getURL(String... args) {
        if (args.length == 1) {
            return args[0];
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            stringBuilder.append(args[i]);
            if (i != args.length - 1) {
                stringBuilder.append("/");
            }
        }
        return stringBuilder.toString();
    }
}


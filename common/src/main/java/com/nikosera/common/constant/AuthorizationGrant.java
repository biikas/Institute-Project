package com.nikosera.common.constant;

public interface AuthorizationGrant {
    String ROLE_PREFIX = "ROLE_";
    String TOTP = "hasAuthority('TOTP')";

    String USER = "hasAuthority('USER')";
    String VIEW_USER = "hasAuthority('VIEW_USER')";
    String CREATE_USER = "hasAuthority('CREATE_USER')";
    String MANAGE_USER = "hasAuthority('MANAGE_USER')";

    String GROUP_PERMISSION = "hasAuthority('GROUP_PERMISSION')";
    String VIEW_GROUP_PERMISSION = "hasAuthority('VIEW_GROUP_PERMISSION')";
    String CREATE_GROUP_PERMISSION = "hasAuthority('CREATE_GROUP_PERMISSION')";
    String MANAGE_GROUP_PERMISSION = "hasAuthority('MANAGE_GROUP_PERMISSION')";

    String AND = " and ";
    String OR = " or ";

    static String PERMISSION(String... args) {
        String finalPermission = "";
        for (int i = 0; i < args.length; i++) {
            finalPermission += args[i];
            if (i + 1 != args.length) {
                finalPermission += " ";
            }
        }
        return finalPermission;
    }
}

package com.nikosera.cas.constant;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public enum JwtTokenConstant {
    AUDIENCE("user"),
    ISSUER("Nikosera"),
    USER_AGENT("user-agent"),
    USER_IP("user-ip"),
    TOTP("TOTP"),
    SECRET("secret");

    private String name;

    JwtTokenConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

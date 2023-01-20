package com.nikosera.cas.totp.algorithm;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public enum HashingAlgorithm {

    SHA1("HmacSHA1", "SHA1"),
    SHA256("HmacSHA256", "SHA256"), // not supported
    SHA512("HmacSHA512", "SHA512");

    private final String hmacAlgorithm;
    private final String friendlyName;

    HashingAlgorithm(String hmacAlgorithm, String friendlyName) {
        this.hmacAlgorithm = hmacAlgorithm;
        this.friendlyName = friendlyName;
    }

    public String getHmacAlgorithm() {
        return hmacAlgorithm;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}

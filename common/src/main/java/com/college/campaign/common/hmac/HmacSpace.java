package com.college.campaign.common.hmac;


/*
 * @Author Rashim Dhaubanjar
 */
public class HmacSpace extends HmacSignatureBuilder {

    private static final byte delimeter = ' ';

    public HmacSpace() {
        super(delimeter);
    }
}

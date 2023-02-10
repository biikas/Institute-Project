package com.f1soft.campaign.common.hmac;


/*
 * @Author Rashim Dhaubanjar
 */
public class HmacFeedLine extends HmacSignatureBuilder {

    private static final byte delimeter = '\n';

    public HmacFeedLine() {
        super(delimeter);
    }
}

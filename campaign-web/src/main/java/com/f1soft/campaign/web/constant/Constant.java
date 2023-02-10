package com.f1soft.campaign.web.constant;

public class Constant {

    public interface Channel {
        String WEB = "WEB";
        String MOBILE = "MOBILE";
    }

    public interface AdminType {
        String SUPER_ADMIN = "SUPER_ADMIN";
        String ADMIN = "ADMIN";
    }

    public interface CampaignStatus {
        String ACTIVE = "ACTIVE";
        String COMPLETED = "COMPLETED";
        String CANCELLED = "CANCELLED";
    }

    public interface DefaultCampaignPromoCode {
        String DEFAULT_PROMO_CODE = "REDEEM";
    }

    private interface OfferTransactionStatus {
        String ACTIVE = "ACTIVE";
        String PENDING = "PENDING";
        String SUCCESS = "SUCCESS";
        String FAILED = "FAILED";
    }

    public interface ServiceMode {
        String MANUAL = "MANUAL";
        String SERVICE = "SERVICE";
    }

}

package com.college.campaign.client.constant;

/**
 * @author Prajwol hada
 */
public class Constant {

    public interface BookingStatus{
        String BOOKED = "BOOKED";
        String PAID = "PAID";
    }

    public interface DataConfig {
        String HMAC_CLIENT_DATA = "DATA_CONFIG_HMAC_CLIENT";
    }

    public interface Time {
        String ONE_MIN = "ONE_MIN";
        String FIVE_MIN = "FIVE_MIN";
        String ONE_HOUR = "ONE_HOUR";
        String ETERNAL = "ETERNAL";
    }
}

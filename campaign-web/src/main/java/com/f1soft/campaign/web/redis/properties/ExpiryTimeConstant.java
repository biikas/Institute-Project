package com.f1soft.campaign.web.redis.properties;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
public interface ExpiryTimeConstant {

    Map<String, Duration> EXPIRY = new HashMap<String, Duration>() {{
        put(Time.ONE_MIN, Duration.ofMinutes(1));
        put(Time.FIVE_MIN, Duration.ofMinutes(5));
        put(Time.ONE_HOUR, Duration.ofHours(1));
        put(Time.ETERNAL, Duration.ofSeconds(-1));
    }};

    interface Time {
        String ONE_MIN = "ONE_MIN";
        String FIVE_MIN = "FIVE_MIN";
        String ONE_HOUR = "ONE_HOUR";
        String ETERNAL = "ETERNAL";
    }
}

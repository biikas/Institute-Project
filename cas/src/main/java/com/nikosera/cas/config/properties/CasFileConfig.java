package com.nikosera.cas.config.properties;

import com.nikosera.common.dto.ModelBase;
import com.nikosera.common.util.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "cas")
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:cas.yml")
public class CasFileConfig extends ModelBase {

    private String jwtHeader;
    private String authBearerPrefix;
    private HashId hashId;
    private int resourceExpiresIn; //in seconds
    private String aesSecretKey;
    private String rsaKey;
    private int jwtExpiresInAuthenticatedPhase; //in minutes
    private int jwtExpiresInTotpPhase; //in minutes
    private int jwtExpiresInQrScanPhase; //in minutes
    private Totp totp;

    @Getter
    @Setter
    public static class HashId extends ModelBase {
        private String salt;
        private Integer minHashLength;
    }
    public static class Totp extends ModelBase {
        @Getter
        @Setter
        private Boolean force = true;
        private static final int DEFAULT_CODE_LENGTH = 6;
        private static final int DEFAULT_TIME_PERIOD = 30;
        private static final int DEFAULT_TIME_DISCREPANCY = 1;
        @Getter
        @Setter
        private final Code code = new Code();
        @Getter
        @Setter
        private final Time time = new Time();

        @Getter
        @Setter
        public static class Code {
            private int length = DEFAULT_CODE_LENGTH;
        }
        @Getter
        @Setter
        public static class Time {
            private int period = DEFAULT_TIME_PERIOD;
            private int discrepancy = DEFAULT_TIME_DISCREPANCY;

        }
    }
}

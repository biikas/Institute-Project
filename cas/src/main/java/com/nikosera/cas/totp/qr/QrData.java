package com.nikosera.cas.totp.qr;

import com.nikosera.cas.totp.algorithm.HashingAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@AllArgsConstructor
public class QrData {

    private final String type;
    private final String label;
    private final String secret;
    private final String issuer;
    private final String algorithm;
    private final int digits;
    private final int period;


    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public String getSecret() {
        return secret;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public int getDigits() {
        return digits;
    }

    public int getPeriod() {
        return period;
    }

    /**
     * @return The URI/message to encode into the QR image, in the format specified here:
     * https://github.com/google/google-authenticator/wiki/Key-Uri-Format
     */
    public String getUri() {
        String uri = "otpauth://" +
                uriEncode(type) + "/" +
                uriEncode(label) + "?" +
                "secret=" + uriEncode(secret) +
                "&issuer=" + uriEncode(issuer) +
                "&algorithm=" + uriEncode(algorithm) +
                "&digits=" + digits +
                "&period=" + period;
        log.debug("otp uri : {}",uri);
        return uri;
    }

    private String uriEncode(String text)  {
        // Null check
        if (text == null) {
            return "";
        }

        try {
            return URLEncoder.encode(text, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            // This should never throw, as we are certain the charset specified (UTF-8) is valid
            throw new RuntimeException("Could not URI encode QrData.");
        }
    }

    public static class Builder {
        private String label;
        private String secret;
        private String issuer;
        private HashingAlgorithm algorithm = HashingAlgorithm.SHA1;
        private int digits = 6;
        private int period = 30;

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public Builder secret(String secret) {
            this.secret = secret;
            return this;
        }

        public Builder issuer(String issuer) {
            this.issuer = issuer;
            return this;
        }

        public Builder algorithm(HashingAlgorithm algorithm) {
            this.algorithm = algorithm;
            return this;
        }

        public Builder digits(int digits) {
            this.digits = digits;
            return this;
        }

        public Builder period(int period) {
            this.period = period;
            return this;
        }

        public QrData build() {
            return new QrData("totp", label, secret, issuer, algorithm.getFriendlyName(), digits, period);
        }
    }
}


package com.f1soft.campaign.web.hmac;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HmacUtil {

    private static final Pattern AUTHORIZATION_HEADER_PATTERN = Pattern.compile("^(\\w+) (\\S+):(\\S+):([\\S]+)$");

    public static AuthHeader getAuthHeader(HttpServletRequest request) {

        final String authHeader = request.getHeader(AuthHeader.AUTHORIZATION_HEADER);
        if (authHeader == null) {
            // invalid authorization token
            return null;
        }

        final Matcher authHeaderMatcher = AUTHORIZATION_HEADER_PATTERN.matcher(authHeader);
        if (!authHeaderMatcher.matches()) {
            // invalid authorization token
            return null;
        }

        final String algorithm = authHeaderMatcher.group(1);
        final String apiKey = authHeaderMatcher.group(2);
        final String nonce = authHeaderMatcher.group(3);
        final String receivedDigest = authHeaderMatcher.group(4);

        return new AuthHeader(algorithm, apiKey, nonce, DatatypeConverter.parseBase64Binary(receivedDigest));
    }
}

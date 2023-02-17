package com.f1soft.campaign.web.hmac;

import com.f1soft.campaign.entities.model.Client;
import com.f1soft.campaign.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static java.util.Optional.ofNullable;

public class HmacAccessFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger log = LoggerFactory.getLogger(HmacAccessFilter.class);

    private final ClientRepository clientRepository;

    public HmacAccessFilter(final RequestMatcher requiresAuth, ClientRepository clientRepository) {
        super(requiresAuth);
        this.clientRepository = clientRepository;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (!requiresAuthentication(request, response)) {
            chain.doFilter(request, response);

            return;
        }

        CachingRequestWrapper requestWrapper = new CachingRequestWrapper(request);
        requestWrapper.getContentAsByteArray();

        Authentication authResult;

        try {
            authResult = attemptAuthentication(requestWrapper, response);
            if (authResult == null) {
                // return immediately as subclass has indicated that it hasn't completed
                // authentication
                return;
            }
        } catch (InternalAuthenticationServiceException failed) {
            logger.error(
                    "An internal error occurred while trying to authenticate the user.",
                    failed);
            unsuccessfulAuthentication(requestWrapper, response, failed);

            return;
        } catch (AuthenticationException failed) {
            // Authentication failed
            unsuccessfulAuthentication(requestWrapper, response, failed);

            return;
        }
        successfulAuthentication(requestWrapper, response, chain, authResult);
    }

    public Authentication attemptAuthentication(
            HttpServletRequest request,
            final HttpServletResponse response) throws IOException, ServletException {


        log.debug("Hmac access filter");

        final String param = ofNullable(request.getHeader(AUTHORIZATION))
                .orElse(request.getParameter("t"));

        final String token = ofNullable(param)
                .map(String::trim)
                .orElseThrow(() -> new InternalAuthenticationServiceException("Missing Authentication Token"));

        final AuthHeader authHeader = HmacUtil.getAuthHeader(request);

        if (authHeader == null) {
            // invalid authorization token
            log.warn("Authorization header is missing");
            throw new InternalAuthenticationServiceException("Missing Authentication Token");
        }

        final String apiKey = authHeader.getApiKey();

        Optional<Client> client = clientRepository.findByUsername(apiKey);

        if (!client.isPresent()) {
            // invalid digest
            log.error("Invalid Credential");
            throw new InternalAuthenticationServiceException("Invalid Credential data");
        }

        if (client.get().getActive() != 'Y') {
            log.error("Client account blocked");
            throw new InternalAuthenticationServiceException("Client account is blocked");
        }

        final byte[] apiSecret = client.get().getApiSecret().getBytes(StandardCharsets.UTF_8);

        CachingRequestWrapper requestWrapper = new CachingRequestWrapper(request);

        final byte[] contentAsByteArray = requestWrapper.getContentAsByteArray();

        log.debug("algorithm : " + authHeader.getAlgorithm());
        log.debug("scheme : " + request.getScheme());
        log.debug("host : " + request.getServerName());
        log.debug("method : " + request.getMethod());
        log.debug("request uri : " + request.getRequestURI());
        log.debug("content type : " + request.getContentType());
        log.debug("nonce : " + authHeader.getNonce());
        log.debug("api fieldNo : " + apiKey);
        log.debug("api secret : " + new String(apiSecret));
        log.debug("content : " + new String(contentAsByteArray));

        final HmacSignatureBuilder signatureBuilder = new HmacSignatureBuilder()
                .algorithm(authHeader.getAlgorithm())
                .nonce(authHeader.getNonce())
                .apiKey(authHeader.getApiKey())
                .apiSecret(client.get().getApiSecret())
                .payload(contentAsByteArray);

        if (!signatureBuilder.isHashEquals(authHeader.getDigest())) {
            // invalid digest
            log.error("Invalid digest");
            throw new InternalAuthenticationServiceException("Invalid authorization data");
        }

        final PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(client.get(), null, null);
        return authentication;
    }

    @Override
    protected void successfulAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain,
            final Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}

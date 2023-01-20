package com.nikosera.cas.util;

import com.nikosera.cas.config.properties.CasFileConfig;
import com.nikosera.cas.constant.JwtTokenConstant;
import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.common.exception.TokenGenerationException;
import com.nikosera.entity.ApplicationUser;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.nikosera.common.constant.MsgConstant.Authorization.JWT.TOKEN_GENERATION_FAILURE;

@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TokenBuilder {

    private final TokenManager tokenManager;
    private final CasFileConfig config;

    public Token buildToken(CustomUserDetail customUserDetail) {
        try {
            Date createdDate = new Date();
            Date expiryDate;
            JWTClaimsSet jwtClaimsSet;

            if (!customUserDetail.hasForcedMfa() && config.getTotp().getForce() && !customUserDetail.isMfaEnabled()) { // For first time TOTP verification
                expiryDate = tokenManager.calculateExpirationDateTotpFirstTime(createdDate);
                jwtClaimsSet = buildClaimForTotp(customUserDetail.getCurrentUser(), createdDate, expiryDate);
            } else if (customUserDetail.isMfaEnabled()) { // For Normal TOTP verification
                expiryDate = tokenManager.calculateExpirationDateTotp(createdDate);
                jwtClaimsSet = buildClaimForTotp(customUserDetail.getCurrentUser(), createdDate, expiryDate);
            } else {
                expiryDate = tokenManager.calculateExpirationDate(createdDate); // For TOTP disabled user
                jwtClaimsSet = buildClaim(customUserDetail.getCurrentUser(), createdDate, expiryDate);
            }

            return Token
                    .builder()
                    .createdDate(createdDate)
                    .expiryDate(expiryDate)
                    .token(tokenManager.toEncryptedToken(jwtClaimsSet))
                    .build();
        } catch (Exception e) {
            log.error("JWT TOKEN EXCEPTION: Failed to build token");
            throw new TokenGenerationException(TOKEN_GENERATION_FAILURE);
        }
    }

    public Token normalAuthToken(CustomUserDetail customUserDetail) {
        try {
            Date createdDate = new Date();
            Date expiryDate = tokenManager.calculateExpirationDate(createdDate);

            JWTClaimsSet jwtClaimsSet = buildClaim(customUserDetail.getCurrentUser(), createdDate, expiryDate);

            return Token
                    .builder()
                    .createdDate(createdDate)
                    .expiryDate(expiryDate)
                    .token(tokenManager.toEncryptedToken(jwtClaimsSet))
                    .build();
        } catch (Exception e) {
            log.error("JWT TOKEN EXCEPTION: Failed to build token");
            throw new TokenGenerationException(TOKEN_GENERATION_FAILURE);
        }
    }

    private JWTClaimsSet buildClaim(ApplicationUser applicationUser, Date createdDate, Date expiryDate) {

        log.debug("Authenticated token created: {}", createdDate);
        log.debug("Authenticated token expires: {}", expiryDate);

        JWTClaimsSet.Builder jwtClaimsSet = new JWTClaimsSet.Builder()
                .issueTime(createdDate)
                .expirationTime(expiryDate)
                .audience(JwtTokenConstant.AUDIENCE.getName())
                .subject(applicationUser.getUsername())
                .issuer(JwtTokenConstant.ISSUER.getName());

        return jwtClaimsSet.build();
    }

    private JWTClaimsSet buildClaimForTotp(ApplicationUser applicationUser, Date createdDate, Date expiryDate) {

        log.debug("TOTP token created: {}", createdDate);
        log.debug("TOTP token expires: {}", expiryDate);

        JWTClaimsSet.Builder jwtClaimsSet = new JWTClaimsSet.Builder()
                .issueTime(createdDate)
                .expirationTime(expiryDate)
                .issuer(JwtTokenConstant.ISSUER.getName());

        if (applicationUser.getIsMfaEnabled() == 'Y') {
            jwtClaimsSet.claim(JwtTokenConstant.TOTP.getName(), false);
        } else {
            jwtClaimsSet.claim(JwtTokenConstant.TOTP.getName(), true);
        }

        jwtClaimsSet.claim(JwtTokenConstant.SECRET.getName(), applicationUser.getSecret());
        return jwtClaimsSet.build();
    }
}

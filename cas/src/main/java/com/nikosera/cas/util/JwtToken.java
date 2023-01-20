package com.nikosera.cas.util;

import com.nikosera.cas.constant.JwtTokenConstant;
import com.nikosera.common.exception.JwtTokenExpiredException;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.function.Function;

import static com.nikosera.common.constant.MsgConstant.Authorization.SESSION_EXPIRED;

@Slf4j
@Getter
@Setter
public class JwtToken {

    private JWTClaimsSet jwtClaimsSet;
    private Date expirationDate;
    private String issuer;
    private String username;

    public JwtToken(JWTClaimsSet jwtClaimsSet) {
        this.jwtClaimsSet = jwtClaimsSet;
        this.expirationDate = jwtClaimsSet.getExpirationTime();
        this.issuer = jwtClaimsSet.getIssuer();
        this.username = jwtClaimsSet.getSubject();
    }

    private <T> T getFromJWTClaimSet(Function<JWTClaimsSet, T> claimsResolver) {
        return claimsResolver.apply(this.jwtClaimsSet);
    }

    public String getSecret() {
        return (String) getFromJWTClaimSet(jwtClaimsSet -> jwtClaimsSet.getClaim(JwtTokenConstant.SECRET.getName()));
    }

    public Boolean isTotpEnabled() {
        return (Boolean) getFromJWTClaimSet(jwtClaimsSet -> jwtClaimsSet.getClaim(JwtTokenConstant.TOTP.getName()));
    }

    public String getUsername() {
        return getFromJWTClaimSet(JWTClaimsSet::getSubject);
    }

    public Boolean isTokenExpired() {
        if (expirationDate.before(new Date())) {
            log.error("JWT TOKEN EXCEPTION: Token is expired");
            throw new JwtTokenExpiredException(SESSION_EXPIRED);
        }
        return false;
    }

}

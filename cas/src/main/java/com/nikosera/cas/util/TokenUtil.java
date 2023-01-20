package com.nikosera.cas.util;

import com.nikosera.cas.constant.JwtTokenConstant;
import com.nikosera.common.dto.CustomUserDetail;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenUtil {

    @SneakyThrows
    public boolean validateForSecretKey(JwtToken jwtToken, UserDetails userDetails) {
        CustomUserDetail user = (CustomUserDetail) userDetails;
        return (!jwtToken.isTokenExpired() && (JwtTokenConstant.ISSUER.getName().equals(jwtToken.getIssuer())) && (user.getSecretKey().equals(jwtToken.getSecret())));
    }

    @SneakyThrows
    public boolean validateForUsername(JwtToken jwtToken, UserDetails userDetails) {
        CustomUserDetail user = (CustomUserDetail) userDetails;
        return (!jwtToken.isTokenExpired() && (JwtTokenConstant.ISSUER.getName().equals(jwtToken.getIssuer())) && (user.getUsername().equals(jwtToken.getUsername())));
    }
}

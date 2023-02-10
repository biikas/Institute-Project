package com.f1soft.campaign.web.config.provider;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.exception.UnauthorizedException;
import com.f1soft.campaign.entities.model.ApplicationUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/*
 * @Author Rashim Dhaubanjar
 */

@Slf4j
public class LoginProvider {

    public static ApplicationUser getApplicationUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            ApplicationUser user = ((AuthApplicationUser) authentication.getPrincipal()).getUser();
            if (user == null) {
                throw new UnauthorizedException(MsgConstant.TOKEN_NOT_FOUND);
            }
            return user;
        } catch (Exception e) {
            throw new UnauthorizedException(MsgConstant.TOKEN_NOT_FOUND);
        }
    }

    public static Optional<ApplicationUser> findApplicationUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            ApplicationUser user = ((AuthApplicationUser) authentication.getPrincipal()).getUser();
            if (user == null) {
                return Optional.empty();
            }
            return Optional.ofNullable(user);
        } catch (Exception e) {
            log.error("Error : ", e.getMessage());
            return Optional.empty();
        }
    }
}


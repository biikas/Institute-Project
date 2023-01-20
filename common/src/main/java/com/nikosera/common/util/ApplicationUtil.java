package com.nikosera.common.util;

import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.common.exception.UnauthorizedException;
import com.nikosera.entity.ApplicationUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */

@Component
@Slf4j
public class ApplicationUtil {

    public UserDetails getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Unauthorized");
        }
        return (UserDetails) authentication.getPrincipal();
    }

    public ApplicationUser loggedInUser() {
        ApplicationUser applicationUser = ((CustomUserDetail) getAuthenticatedUser()).getCurrentUser();
        log.info("User: username- {} id- {} ", applicationUser.getUsername(), applicationUser.getId());
        return applicationUser;
    }
}

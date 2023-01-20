package com.nikosera.cas.audit;

import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.common.exception.UnauthorizedException;
import com.nikosera.entity.ApplicationUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.function.Supplier;


@Slf4j
@Component
public class AuditAwareImpl implements AuditorAware<ApplicationUser> {

    @Autowired
    private HttpServletRequest request;

    @Override
    public Optional<ApplicationUser> getCurrentAuditor() {

        String path = new UrlPathHelper().getPathWithinApplication(request);

        ApplicationUser principal = ((CustomUserDetail) getAuthenticatedUser()).getCurrentUser();

        if (!ObjectUtils.isEmpty(principal)) {
            log.info("::: :::: CURRENT AUDITOR ::: :: {}", principal);
            return Optional.of(principal);
        } else {
            throw UNAUTHORIZED.get();
        }
    }

    public UserDetails getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw UNAUTHORIZED.get();
        }
        return (UserDetails) authentication.getPrincipal();
    }

    private Supplier<UnauthorizedException> UNAUTHORIZED = () -> {
        log.error("::: :::: CURRENT AUDITOR NOT AUTHORZIED ::: :: ");
        throw new UnauthorizedException("Unauthorized");
    };


}

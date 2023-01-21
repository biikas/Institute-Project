package com.nikosera.clientweb.remoablecontroller;

import com.nikosera.cas.exception.ResourceNotFoundException;
import com.nikosera.cas.security.CurrentUser;
import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.repository.repository.core.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CurrentUserController {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public ApplicationUser getCurrentUser(@CurrentUser CustomUserDetail userPrincipal) {
        return applicationUserRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}

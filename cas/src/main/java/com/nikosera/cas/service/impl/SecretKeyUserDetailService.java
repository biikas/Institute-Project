package com.nikosera.cas.service.impl;

import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.common.exception.InvalidTokenException;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.repository.repository.core.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Service("secretKeyUserDetailService")
@AllArgsConstructor
public class SecretKeyUserDetailService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public CustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> optionalApplicationUser = applicationUserRepository.findBySecret(username);
        /**
         * If the user with the secret key is not found then it mean someone have already changed the secret key from another session
         */
        if (!optionalApplicationUser.isPresent()) {
            throw new InvalidTokenException("Invalid OTP");
        }
        return optionalApplicationUser.map(CustomUserDetail::new).get();
    }
}

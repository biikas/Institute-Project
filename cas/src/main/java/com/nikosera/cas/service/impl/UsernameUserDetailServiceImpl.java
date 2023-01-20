package com.nikosera.cas.service.impl;

import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.common.exception.UserNotFoundException;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.repository.repository.core.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.nikosera.common.constant.MsgConstant.Authorization.INCORRECT_CREDENTIAL;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Service("usernameUserDetailService")
@AllArgsConstructor
public class UsernameUserDetailServiceImpl implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public CustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> optionalApplicationUser = applicationUserRepository.findByUsername(username);
        if (!optionalApplicationUser.isPresent()) {
            throw new UserNotFoundException(INCORRECT_CREDENTIAL);
        }
        return optionalApplicationUser.map(CustomUserDetail::new).get();
    }
}

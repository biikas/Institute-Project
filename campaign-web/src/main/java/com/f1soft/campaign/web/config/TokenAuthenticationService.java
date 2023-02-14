package com.f1soft.campaign.web.config;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.exception.UnauthorizedException;
import com.f1soft.campaign.entities.model.ApplicationUser;
import com.f1soft.campaign.repository.ApplicationUserRepository;
import com.f1soft.campaign.web.auth.TokenUtil;
import com.f1soft.campaign.web.config.provider.AuthApplicationUser;
import com.f1soft.campaign.web.token.TokenDTO;
import com.f1soft.campaign.web.token.TokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class TokenAuthenticationService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private TokenHelper tokenHelper;

    public Optional<UserDetails> findByToken(final String token) {
        try{
            String username = tokenUtil.getUsernameFromToken(token);
            log.debug("Username from token : {}", username);
            Optional<ApplicationUser> applicationUser = applicationUserRepository.findApplicationUserByUsername(username);
            if (!applicationUser.isPresent()) {
                return Optional.empty();
            }
            return Optional.ofNullable(new AuthApplicationUser(applicationUser.get(), Arrays.asList(new SimpleGrantedAuthority(applicationUser.get().getAdminTypeName()))));
        }catch (Exception e){
            throw new UnauthorizedException(MsgConstant.INVALID_LOGIN_TOKEN);
        }
    }
}

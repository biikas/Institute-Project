package com.f1soft.campaign.web.login;


import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.exception.UnauthorizedException;
import com.f1soft.campaign.entities.model.AdminRolesProfile;
import com.f1soft.campaign.entities.model.ApplicationUser;
import com.f1soft.campaign.repository.AdminRolesProfileRepository;
import com.f1soft.campaign.web.auth.AuthenticationRequest;
import com.f1soft.campaign.web.auth.TokenUtil;
import com.f1soft.campaign.web.dto.MenuDTO;
import com.f1soft.campaign.web.login.dto.LoginResponse;
import com.f1soft.campaign.web.manager.ApplicationUserManager;
import com.f1soft.campaign.web.mapper.MenuMapper;
import com.f1soft.campaign.web.token.TokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.stream.Collectors;

/*
 * @Author Rashim Dhaubanjar
 */
@Slf4j
@Component
@RequestScope
public class Login {
    @Autowired
    protected ApplicationUserManager applicationUserManager;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private TokenHelper tokenHelper;
    @Autowired
    private AdminRolesProfileRepository adminRolesProfileRepository;

    protected ApplicationUser applicationUser;
    protected AuthenticationRequest authenticationRequest;
    protected ServerResponse serverResponse;

    private void init(AuthenticationRequest authenticationRequest) {
        this.authenticationRequest = authenticationRequest;
    }


    public ServerResponse authenticate(AuthenticationRequest authenticationRequest) {
        serverResponse = new ServerResponse();
        applicationUser = applicationUserManager.loadUserByUsername(authenticationRequest.getUsername());

        init(authenticationRequest);

        serverResponse = authenticate();

        return serverResponse;
    }


    private ServerResponse authenticate() {
        LoginResponse loginResponse = new LoginResponse();
        if (applicationUser.getActive() == 'Y') {
            validateCredential();
            List<AdminRolesProfile> adminRolesProfileList = adminRolesProfileRepository.loadAllActiveRolesByAdminType(applicationUser.getAdminType().getId());
            loginResponse.setUsername(applicationUser.getUsername());
            loginResponse.setName(applicationUser.getName());
            loginResponse.setEmailAddress(applicationUser.getEmailAddress());
            loginResponse.setType(applicationUser.getAdminTypeName());
            loginResponse.setRoles(convertToAdminRoles(adminRolesProfileList));

            String token = generateToken(applicationUser);
            tokenHelper.createToken(token, applicationUser.getUsername());
            loginResponse.setToken(token);
            serverResponse.setSuccess(true);
            serverResponse.setObj(loginResponse);
            return serverResponse;

        } else {
            throw new UnauthorizedException(MsgConstant.Admin.LOGIN_BLOCKED);
        }
    }

    public ServerResponse validateCredential() {
        boolean valid = bcrypt.matches(authenticationRequest.getPassword(), applicationUser.getPassword());

        if (valid) {
            serverResponse.setSuccess(true);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(applicationUser, null));
            return serverResponse;
        } else {
            throw new UnauthorizedException(MsgConstant.INVALID_USERNAME_PASSWORD);
        }
    }

    public String generateToken(ApplicationUser applicationUser) {
        return tokenUtil.generateToken(applicationUser.getUsername(), "WEB");
    }

    private List<MenuDTO> convertToAdminRoles(List<AdminRolesProfile> adminRolesProfileList) {
        List<MenuDTO> menuDTOList = adminRolesProfileList.stream()
                .filter(adminRoles -> adminRoles.getAdminMenu().getActive() == 'Y')
                .map(adminRoles -> MenuMapper.convertToMenuDTO(adminRoles.getAdminMenu()))
                .collect(Collectors.toList());
        return menuDTOList;
    }
}

package com.nikosera.cas.builder;

import com.nikosera.cas.constant.JwtTokenConstant;
import com.nikosera.common.constant.AuthorizationGrant;
import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.entity.UserGroup;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class AuthPermissionBuilder {

    public static void buildUsernamePasswordValidatedTOTPEnabled(CustomUserDetail customUserDetail) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(JwtTokenConstant.TOTP.getName()));
        customUserDetail.setAuthorities(grantedAuthorities);
    }

    public static void buildForFullyAuthenticated(CustomUserDetail customUserDetail) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        ApplicationUser currentUser = customUserDetail.getCurrentUser();
        UserGroup userGroup = currentUser.getUserGroup();

        if (userGroup != null && userGroup.getActive() == 'Y') {
            grantedAuthorities.add(new SimpleGrantedAuthority(AuthorizationGrant.ROLE_PREFIX + currentUser.getUserGroup().getName())); // Adding current user group name
            grantedAuthorities.addAll(userGroup.getPermissions().stream()
                    .filter((permission) -> permission.getEnabled() == 'Y')
                    .map((permission) -> new SimpleGrantedAuthority(permission.getUserMenu().getAction())).collect(Collectors.toList())); // Adding list of permission
        }

        customUserDetail.setAuthorities(grantedAuthorities);
    }

    public static void buildForUsernamePasswordValidatedTOTPDisabled(CustomUserDetail customUserDetail, Boolean isEnforced) {
        if ((isEnforced && !customUserDetail.hasForcedMfa()) || customUserDetail.isMfaEnabled()) {
            buildUsernamePasswordValidatedTOTPEnabled(customUserDetail);
        } else {
            buildForFullyAuthenticated(customUserDetail);
        }
    }
}

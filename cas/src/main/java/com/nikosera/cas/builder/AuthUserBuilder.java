package com.nikosera.cas.builder;

import com.nikosera.entity.ApplicationUser;

import java.util.Date;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class AuthUserBuilder {

    public static void buildFirstTimeTOTPAuthUser(ApplicationUser applicationUser){
        applicationUser.setIsMfaEnabled('Y');
        applicationUser.setHasEnforcedMfa('Y');
        applicationUser.setLastModifiedDate(new Date());
    }
}

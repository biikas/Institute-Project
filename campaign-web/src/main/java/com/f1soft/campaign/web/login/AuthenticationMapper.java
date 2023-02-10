package com.f1soft.campaign.web.login;


import com.f1soft.campaign.web.auth.AuthenticationRequest;

/*
 * @Author Rashim Dhaubanjar
 */
public class AuthenticationMapper {

    private AuthenticationMapper() {
    }

    public static AuthenticationRequest convertToAuthenticationRequest(String username,
                                                                       String password
    ) {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername(username);
        authenticationRequest.setPassword(password);

        return authenticationRequest;
    }
}

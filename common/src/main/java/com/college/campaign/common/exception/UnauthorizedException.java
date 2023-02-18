package com.college.campaign.common.exception;

import org.springframework.security.core.AuthenticationException;

public class UnauthorizedException extends AuthenticationException {

    public UnauthorizedException(final String message) {
        super(message);
    }
}

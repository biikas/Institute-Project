package com.nikosera.common.exception;

import org.springframework.security.core.AuthenticationException;

public class UserPermanentBlockedException extends AuthenticationException {

    public UserPermanentBlockedException(String message) {
        super(message);
    }
}

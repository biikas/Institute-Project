package com.nikosera.common.exception;

import org.springframework.security.core.AuthenticationException;

public class UserTemporarilyBlockedException extends AuthenticationException {

    public UserTemporarilyBlockedException(String message) {
        super(message);
    }
}

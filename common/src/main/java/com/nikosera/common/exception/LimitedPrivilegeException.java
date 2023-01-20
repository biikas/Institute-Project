package com.nikosera.common.exception;

import org.springframework.security.core.AuthenticationException;

public class LimitedPrivilegeException extends AuthenticationException {

    public LimitedPrivilegeException(final String msg) {
        super(msg);
    }
}

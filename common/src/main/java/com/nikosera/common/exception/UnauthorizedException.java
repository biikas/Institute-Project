package com.nikosera.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class UnauthorizedException extends AuthenticationException {

    public UnauthorizedException(final String message) {
        super(message);
    }
}
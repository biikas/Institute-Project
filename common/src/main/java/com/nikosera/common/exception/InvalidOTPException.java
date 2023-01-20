package com.nikosera.common.exception;


import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author Narayan Joshi <narenzoshi@gmail.com>
 */
public class InvalidOTPException extends AuthenticationException {

    public InvalidOTPException(final String message) {
        super(message);
    }
}

package com.nikosera.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author Narayan Joshi <narenzoshi@gmail.com>
 */
public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException(final String message) {
        super(message);
    }

}

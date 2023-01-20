package com.nikosera.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author Narayan Joshi <narenzoshi@gmail.com>
 */
public class JwtTokenMissingException extends AuthenticationException {

    public JwtTokenMissingException(final String message) {
        super(message);
    }

}

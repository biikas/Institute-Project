package com.nikosera.common.exception;


import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author Narayan Joshi <narenzoshi@gmail.com>
 */
public class JwtTokenMalformedException extends AuthenticationException {

    public JwtTokenMalformedException(final String message) {
        super(message);
    }
}

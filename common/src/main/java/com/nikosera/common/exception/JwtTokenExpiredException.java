package com.nikosera.common.exception;


import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author Narayan Joshi <narenzoshi@gmail.com>
 */
public class JwtTokenExpiredException extends AuthenticationException {

    public JwtTokenExpiredException(final String message) {
        super(message);
    }

}

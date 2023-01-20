package com.nikosera.common.exception;


import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author Narayan Joshi <narenzoshi@gmail.com>
 */
public class TokenGenerationException extends AuthenticationException {

    public TokenGenerationException(final String message) {
        super(message);
    }
}

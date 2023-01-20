package com.nikosera.common.exception;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
public class NoResultFoundException extends RuntimeException {

    public NoResultFoundException(final String message) {
        super(message);
    }
}
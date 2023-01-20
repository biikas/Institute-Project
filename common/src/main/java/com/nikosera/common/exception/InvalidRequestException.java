package com.nikosera.common.exception;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(final String message) {
        super(message);
    }
}

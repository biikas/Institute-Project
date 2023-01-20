package com.nikosera.common.exception;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class TimeProviderException extends RuntimeException {
    public TimeProviderException(String message) {
        super(message);
    }
    public TimeProviderException(String message, Throwable cause) {
        super(message, cause);
    }
}


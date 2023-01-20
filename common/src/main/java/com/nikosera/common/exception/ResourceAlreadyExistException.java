package com.nikosera.common.exception;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */

public class ResourceAlreadyExistException  extends RuntimeException {

    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}

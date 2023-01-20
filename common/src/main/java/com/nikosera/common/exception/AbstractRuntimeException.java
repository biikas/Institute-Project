package com.nikosera.common.exception;

/**
 * @author narayanjoshi
 * @email <narayan.joshi>
 * Created Date: 12/7/22
 */
abstract class AbstractRuntimeException extends RuntimeException {

    private String code;

    public AbstractRuntimeException(String message) {
        super(message);
    }

    public AbstractRuntimeException(String message, String code) {
        super(message);
        this.code = code;
    }

    AbstractRuntimeException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    String getCode() {
        return code;
    }

}


package com.nikosera.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
@Getter
@Setter
public class GenericResponse extends ModelBase {

    private boolean success;
    private String message;
    private Object data;

    private String statusCode;

    public GenericResponse() {
    }

    public GenericResponse(String message) {
        this.message = message;
    }

    public GenericResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}

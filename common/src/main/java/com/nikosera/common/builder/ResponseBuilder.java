package com.nikosera.common.builder;

import com.nikosera.common.dto.GenericResponse;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */

public class ResponseBuilder {

    public static GenericResponse buildSuccessMessage(Object obj, String message) {
        GenericResponse response = new GenericResponse(true, message);
        response.setData(obj);
        return response;
    }

    public static GenericResponse buildSuccessMessage(String message) {
        GenericResponse response = new GenericResponse(true, message);
        return response;
    }

    public static GenericResponse failureResponse(String message) {
        GenericResponse response = new GenericResponse(false, message);
        return response;
    }

    public static GenericResponse buildResponse(Object obj, boolean status, String message) {
        GenericResponse response = new GenericResponse(status, message);
        if(status){
            response.setData(obj);
        }
        return response;
    }

    public static GenericResponse buildResponse(boolean status, String message) {
        GenericResponse response = new GenericResponse(status, message);
        return response;
    }
}

package com.college.campaign.common.exception;


import com.college.campaign.common.dto.ServerResponse;

public class DataNotFoundException extends ServerException {

    public DataNotFoundException(final String message) {
        super(message);
    }

    public DataNotFoundException(final ServerResponse serverResponse) {
        super(serverResponse);
    }
}

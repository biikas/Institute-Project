package com.college.campaign.common.exception;


import com.college.campaign.common.dto.ServerResponse;

public class IllegalOperationException extends ServerException {

    public IllegalOperationException(final String message) {
        super(message);
    }

    public IllegalOperationException(final ServerResponse serverResponse) {
        super(serverResponse);
    }
}

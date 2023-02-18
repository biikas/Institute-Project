package com.college.campaign.common.exception;


import com.college.campaign.common.dto.ServerResponse;

public class InvalidDataException extends ServerException {

    public InvalidDataException(final String message) {
        super(message);
    }

    public InvalidDataException(final ServerResponse serverResponse) {
        super(serverResponse);
    }
}

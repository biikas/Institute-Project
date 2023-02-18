package com.college.campaign.common.exception;


import com.college.campaign.common.dto.ServerResponse;

public class ResourceAlreadyExistException extends ServerException {

    public ResourceAlreadyExistException(final String message) {
        super(message);
    }

    public ResourceAlreadyExistException(final ServerResponse serverResponse) {
        super(serverResponse);
    }
}

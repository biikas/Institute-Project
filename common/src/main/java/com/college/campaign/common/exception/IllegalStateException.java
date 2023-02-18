package com.college.campaign.common.exception;

import com.college.campaign.common.dto.ServerResponse;
import org.springframework.http.HttpStatus;

/**
 * @author rashim
 */
public class IllegalStateException extends ServerException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_ACCEPTABLE;
    }

    public IllegalStateException(final String message) {
        super(message);
    }

    public IllegalStateException(final ServerResponse serverResponse) {
        super(serverResponse);
    }
}

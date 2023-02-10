package com.f1soft.campaign.common.exception;

import com.f1soft.campaign.common.dto.ServerResponse;
import org.springframework.http.HttpStatus;

/**
 * @author rashim
 */
public class DuplicateRequestException extends ServerException {

    public DuplicateRequestException(String message) {
        super(message);
    }

    public DuplicateRequestException(ServerResponse serverResponse) {
        super(serverResponse);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.OK;
    }

    @Override
    public Integer getCode() {
        return 4000;
    }
}

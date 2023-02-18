package com.college.campaign.common.exception;

import com.college.campaign.common.constant.MsgConstant;
import com.college.campaign.common.dto.ServerResponse;
import com.college.campaign.common.util.ResponseMsg;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServerException extends RuntimeException {

    private ServerResponse serverResponse;

    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public Integer getCode() {
        return null;
    }

    public String getProcessingCode() {
        return null;
    }

    public ServerException() {
        super();
    }

    public ServerException(final String message) {
        super(message);
    }

    public ServerException(final ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

    public ServerResponse getServerResponse() {
        if (serverResponse == null) {
            return ResponseMsg.failureResponse(MsgConstant.INTERNAL_SERVER_ERROR);
        }
        return serverResponse;
    }
}

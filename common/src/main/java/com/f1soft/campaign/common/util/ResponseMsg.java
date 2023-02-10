package com.f1soft.campaign.common.util;


import com.f1soft.campaign.common.dto.Message;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.dto.UnauthorizedResponse;

import java.util.Map;

public class ResponseMsg {

    public static ServerResponse successResponse(String messageKey) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(true);
        serverResponse.setCode("0");
        return MessageComposer.compose(serverResponse, messageKey);
    }

    public static ServerResponse successResponse(String messageKey, Object ojb) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(true);
        serverResponse.setCode("0");
        serverResponse.setObj(ojb);
        return MessageComposer.compose(serverResponse, messageKey);
    }

    public static ServerResponse failureResponse(String messageKey) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setCode("2");
        serverResponse.setSuccess(false);
        return MessageComposer.compose(serverResponse, messageKey);
    }

    public static ServerResponse failureResponse(String messageKey, String respCode) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(false);
        serverResponse.setCode(respCode);
        return MessageComposer.compose(serverResponse, messageKey);
    }

    public static UnauthorizedResponse unauthorizedResponse(String messageKey) {
        Message message = MessageComposer.compose(messageKey);

        UnauthorizedResponse unauthorizedResponse = new UnauthorizedResponse();
        unauthorizedResponse.setSuccess(false);
        unauthorizedResponse.setSessionTimeout("Y");
        unauthorizedResponse.setMessage(message.getMessage());
        return unauthorizedResponse;
    }

    public static ServerResponse failureResponse(String messageKey, Map<String, String> parameters) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(false);
        serverResponse.setCode("2");
        return MessageComposer.compose(serverResponse, messageKey, parameters);
    }

    public static ServerResponse successResponse(String messageKey, Map<String, String> parameters) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(true);
        serverResponse.setCode("0");
        return MessageComposer.compose(serverResponse, messageKey, parameters);
    }
}

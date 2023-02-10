package com.f1soft.campaign.common.util;

import com.f1soft.campaign.common.constant.ResponseCodeConstant;
import com.f1soft.campaign.common.dto.BaseResponse;
import com.f1soft.campaign.common.dto.GenericResponse;
import com.f1soft.campaign.common.dto.ServerResponse;

import java.util.Map;

/*
 * @Author Rashim Dhaubanjar
 */
public class CommonMapper {

    private CommonMapper() {
    }

    public static GenericResponse convertToGenericResponse(ServerResponse serverResponse) {
        GenericResponse genericResponse = new GenericResponse();
        if (serverResponse.isSuccess()) {
            genericResponse.setSuccess(serverResponse.isSuccess());
            genericResponse.setCode(serverResponse.getCode() == null ? "0" : serverResponse.getCode());
            genericResponse.setMessage(serverResponse.getMessage());
            genericResponse.setData(serverResponse.getObj());
        } else {
            genericResponse.setSuccess(serverResponse.isSuccess());
            genericResponse.setCode(serverResponse.getCode());
            genericResponse.setMessage(serverResponse.getMessage());
        }
        return genericResponse;
    }

    public static <T> GenericResponse convertToGenericResponse(T data) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(true);
        genericResponse.setCode(ResponseCodeConstant.SUCCESS);
        genericResponse.setData(data);
        return genericResponse;
    }

    public static GenericResponse convertToMessageGenericResponse(ServerResponse serverResponse) {
        GenericResponse genericResponse = new GenericResponse();
        if (serverResponse.isSuccess()) {
            genericResponse.setSuccess(serverResponse.isSuccess());
            genericResponse.setMessage(serverResponse.getMessage());
            genericResponse.setCode(serverResponse.getCode());
        } else {
            genericResponse.setSuccess(serverResponse.isSuccess());
            genericResponse.setMessage(serverResponse.getMessage());
            genericResponse.setCode(serverResponse.getCode());
        }
        return genericResponse;
    }

    public static GenericResponse convertToGenericResponse(BaseResponse baseResponse, Map<String, Object> objectMap) {
        GenericResponse genericResponse = new GenericResponse();
        if (baseResponse.isSuccess()) {
            genericResponse.setSuccess(baseResponse.isSuccess());
            genericResponse.setMessage(baseResponse.getMessage());
            genericResponse.setData(objectMap);
            genericResponse.setCode(baseResponse.getCode());
        } else {
            genericResponse.setSuccess(baseResponse.isSuccess());
            genericResponse.setMessage(baseResponse.getMessage());
            genericResponse.setCode(baseResponse.getCode());
        }
        return genericResponse;
    }

    public static GenericResponse convertToGenericResponse(BaseResponse baseResponse) {
        GenericResponse genericResponse = new GenericResponse();
        if (baseResponse.isSuccess()) {
            genericResponse.setSuccess(baseResponse.isSuccess());
            genericResponse.setMessage(baseResponse.getMessage());
            genericResponse.setCode(baseResponse.getCode());
        } else {
            genericResponse.setSuccess(baseResponse.isSuccess());
            genericResponse.setMessage(baseResponse.getMessage());
            genericResponse.setCode(baseResponse.getCode());
        }
        return genericResponse;
    }
}

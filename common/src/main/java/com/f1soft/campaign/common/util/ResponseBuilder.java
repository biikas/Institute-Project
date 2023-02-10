package com.f1soft.campaign.common.util;

import com.f1soft.campaign.common.dto.BaseResponse;
import com.f1soft.campaign.common.dto.ServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/*
 * @Author Rashim Dhaubanjar
 */
public class ResponseBuilder {

    public final static ResponseEntity<?> successObject(Object obj) {
        return new ResponseEntity<>(CommonMapper.convertToGenericResponse(obj), HttpStatus.OK);
    }

    public final static ResponseEntity<?> response(ServerResponse serverResponse) {
        if (serverResponse.isSuccess()) {
            return new ResponseEntity<>(CommonMapper.convertToGenericResponse(serverResponse), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(CommonMapper.convertToGenericResponse(serverResponse), HttpStatus.OK);
        }
    }


    public final static ResponseEntity<?> message(ServerResponse serverResponse) {
        if (serverResponse.isSuccess()) {
            return new ResponseEntity<>(CommonMapper.convertToMessageGenericResponse(serverResponse), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(CommonMapper.convertToMessageGenericResponse(serverResponse), HttpStatus.OK);
        }
    }

    public final static ResponseEntity<?> mapResponse(BaseResponse baseResponse, Map<String, Object> objectMap) {
        if (baseResponse.isSuccess()) {
            return new ResponseEntity<>(CommonMapper.convertToGenericResponse(baseResponse, objectMap), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(CommonMapper.convertToGenericResponse(baseResponse), HttpStatus.OK);
        }
    }
}

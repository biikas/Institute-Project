package com.nikosera.clientweb.utiilities;

import com.nikosera.clientweb.dto.ServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Bikash Shah
 */
public class ResponseBuilder {

    public final static ResponseEntity<?> objResponse(ServerResponse serverResponse) {
        if (serverResponse.isSuccess()) {
            return new ResponseEntity<>(serverResponse.getObj(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(serverResponse,HttpStatus.OK);
        }
    }

//    public final static ResponseEntity<?> response(ServerResponse s)

}

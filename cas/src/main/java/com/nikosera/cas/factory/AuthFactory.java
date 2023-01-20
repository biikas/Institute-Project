package com.nikosera.cas.factory;

import com.nikosera.cas.constant.AuthMode;
import com.nikosera.cas.service.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Component
public class AuthFactory {
    @Autowired
    @Qualifier("normalResponse")
    private AuthenticationResponse normalResponse;
    @Autowired
    @Qualifier("qrResponse")
    private AuthenticationResponse qrResponse;
    @Autowired
    @Qualifier("unknownResponse")
    private AuthenticationResponse unknownResponse;


    public AuthenticationResponse authenticationResponse(AuthMode mode) {
        switch (mode) {
            case NORMAL:
                return normalResponse;
            case QR:
                return qrResponse;
            default:
                return unknownResponse;
        }
    }
}

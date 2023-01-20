package com.nikosera.cas.service;

import com.nikosera.cas.auth.request.AuthRequest;
import com.nikosera.cas.auth.request.TOTPAuthRequest;
import com.nikosera.common.dto.GenericResponse;

public interface AuthenticationService {

    GenericResponse login(AuthRequest request);

    GenericResponse verifyTotp(TOTPAuthRequest request);
}

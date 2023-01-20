package com.nikosera.cas.service;

import com.nikosera.cas.util.TokenBuilder;
import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.common.dto.GenericResponse;
import lombok.AllArgsConstructor;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@AllArgsConstructor
public abstract class AuthenticationResponse {

    protected final TokenBuilder tokenBuilder;

    public abstract GenericResponse authResponse(CustomUserDetail customUserDetail);
}

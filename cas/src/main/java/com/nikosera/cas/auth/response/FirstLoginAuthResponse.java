package com.nikosera.cas.auth.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Getter
@Setter
public class FirstLoginAuthResponse extends NormalAuthResponse {
    private String qr;
}

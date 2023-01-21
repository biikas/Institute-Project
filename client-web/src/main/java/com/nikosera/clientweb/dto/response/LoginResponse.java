package com.nikosera.clientweb.dto.response;

import com.nikosera.clientweb.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
public class LoginResponse extends BaseResponse {
    private String username;
    private String name;
    private String token;
    private String emailAddress;
    private String type;
}

package com.f1soft.campaign.web.mapper;

import com.f1soft.campaign.web.token.TokenDTO;

/**
 * @author Prajwol Hada
 */
public class TokenMapper {

    private TokenMapper() {
    }

    public static TokenDTO convertTokenDTO(String token, String username) {
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        tokenDTO.setUsername(username);
        tokenDTO.setPrefix("ADMIN_TOKEN");

        return tokenDTO;
    }

    public static TokenDTO convertToTokenDTO(String token) {
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        return tokenDTO;
    }
}

package com.f1soft.campaign.web.token;

import com.f1soft.campaign.web.mapper.TokenMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Prajwol Hada
 */
@Slf4j
@Component
public class TokenHelper {

    @Autowired
    private TokenRepository tokenRepository;

    public Optional<TokenDTO> getUserToken(String token) {
        try {
            return Optional.ofNullable(tokenRepository.get(token));
        } catch (Exception e) {
            log.error("Token not found in redis store");
            return Optional.empty();
        }
    }

    public void createToken(TokenDTO tokenDTO) {
        tokenRepository.create(tokenDTO);
    }

    public void createToken(String token, String username) {
        TokenDTO tokenDTO = TokenMapper.convertTokenDTO(token, username);
        createToken(tokenDTO);
    }

    public void updateToken(TokenDTO tokenDTO) {
        tokenRepository.update(tokenDTO);
    }
}

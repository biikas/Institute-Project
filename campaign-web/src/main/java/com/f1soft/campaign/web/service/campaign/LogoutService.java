package com.f1soft.campaign.web.service.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.web.token.TokenDTO;

/**
 * @author Prajwol Hada
 */
public interface LogoutService {

    ServerResponse logout(TokenDTO convertToTokenDTO);
}


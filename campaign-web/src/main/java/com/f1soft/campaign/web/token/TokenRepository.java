package com.f1soft.campaign.web.token;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.config.constant.AdminConfigConstant;
import com.f1soft.campaign.web.redis.util.RedisConnectionHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TokenRepository {

    private final SystemConfig systemConfig;

    private final RedisTemplate redisTemplate;
    private final HashOperations hashOperations;

    private final String keyPrefix = "ADMIN_TOKEN";

    @Autowired
    public TokenRepository(RedisTemplate redisTemplate, SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }


    public void create(TokenDTO tokenDTO) {
        removeToken(tokenDTO.getToken());
        String key = tokenDTO.getPrefix() + "_" + tokenDTO.getToken();
        String hashKey = tokenDTO.getToken();

        hashOperations.put(key, hashKey, tokenDTO);
        redisTemplate.expire(key, Integer.parseInt(this.systemConfig.adminConfig(AdminConfigConstant.LOGIN_TOKEN_EXPIRY_INTERVAL)), TimeUnit.MINUTES);
        RedisConnectionHelper.closeConnection(redisTemplate);
    }

    public TokenDTO get(String token) {

        String key = keyPrefix + "_" + token;
        String hashKey = token;

        TokenDTO cachedTokenDetail = (TokenDTO) hashOperations.get(key, hashKey);
        RedisConnectionHelper.closeConnection(redisTemplate);
        return cachedTokenDetail;
    }

    public void removeToken(String token) {

        String key = keyPrefix + "_" + token;

        Set<String> listScanResult = redisTemplate.keys(key + "*");//scan(ScanOptions.scanOptions().match(key + "*").build());
        for (String userKey : listScanResult) {
            redisTemplate.delete(userKey);
        }
        RedisConnectionHelper.closeConnection(redisTemplate);
    }

    public void update(TokenDTO tokenDTO) {
        String key = tokenDTO.getPrefix() + "_" + tokenDTO.getToken();
        String hashKey = tokenDTO.getToken();

        hashOperations.put(key, hashKey, tokenDTO);
        redisTemplate.expire(key, Integer.parseInt(this.systemConfig.adminConfig(AdminConfigConstant.LOGIN_TOKEN_EXPIRY_INTERVAL)), TimeUnit.MINUTES);
        RedisConnectionHelper.closeConnection(redisTemplate);
    }

}

package com.f1soft.campaign.client.loader;


import com.f1soft.campaign.client.constant.Constant;
import com.f1soft.campaign.entities.model.HmacClient;
import com.f1soft.campaign.repository.HmacClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Rashim Dhaubanjar
 */
@Slf4j
@Component
public class DataConfigLoader {

    @Autowired
    private HmacClientRepository hmacClientRepository;

    @Cacheable(value = Constant.DataConfig.HMAC_CLIENT_DATA, cacheManager = Constant.Time.ETERNAL)
    public Map<String, HmacClient> hmacClientMapMap() {
        return hmacClientRepository.findAllByActive().stream().collect(Collectors.toMap(HmacClient::getClientKey, m -> m));
    }
}
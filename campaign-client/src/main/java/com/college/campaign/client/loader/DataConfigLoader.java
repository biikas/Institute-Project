package com.college.campaign.client.loader;


import com.college.campaign.client.constant.Constant;
import com.college.campaign.entities.model.HmacClient;
import lombok.extern.slf4j.Slf4j;
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



    @Cacheable(value = Constant.DataConfig.HMAC_CLIENT_DATA, cacheManager = Constant.Time.ETERNAL)
    public Map<String, HmacClient> hmacClientMapMap() {
        return null;
    }
}

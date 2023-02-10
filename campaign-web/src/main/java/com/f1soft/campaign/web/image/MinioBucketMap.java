package com.f1soft.campaign.web.image;

import com.f1soft.campaign.web.constant.MinioConfigConstant;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Rashim Dhaubanjar
 */

@Component
public class MinioBucketMap {

    public static Map<String, String> BUCKET = ImmutableMap.<String, String>builder()
            .put("OFFER", MinioConfigConstant.OFFER_BUCKET)
            .build();


}

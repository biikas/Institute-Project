package com.f1soft.campaign.web.redis.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
public class RedisConnectionHelper {
    public static void closeConnection(RedisTemplate redisTemplate) {
        try {
            JedisConnectionFactory connectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
            connectionFactory.getConnection().close();
            connectionFactory.destroy();
        } catch (RedisConnectionFailureException e) {
            log.info("Connection closed already");
        }
    }
}

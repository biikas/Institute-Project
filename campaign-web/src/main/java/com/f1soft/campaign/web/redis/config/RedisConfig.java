package com.f1soft.campaign.web.redis.config;


import com.f1soft.campaign.web.redis.bean.CustomRedisTemplate;
import com.f1soft.campaign.web.redis.properties.ExpiryTimeConstant;
import com.f1soft.campaign.web.redis.properties.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@Slf4j
@SuppressWarnings({"Duplicates"})
@EnableCaching
@EnableConfigurationProperties(RedisProperties.class)
@Configuration
public class RedisConfig extends CachingConfigurerSupport implements CachingConfigurer {

    @Autowired
    private RedisProperties redisProperties;

    @PostConstruct
    public void init() {
        log.info("Redis properties : {}", redisProperties);
    }

    private JedisPoolConfig jedisPoolConfig() {
        RedisProperties.RedisPoolConfigProperties poolConfig = redisProperties.getPool();

        final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        /**
         * The maximum number of connections that can be allocated from this pool.
         */
        jedisPoolConfig.setMaxTotal(poolConfig.getMaxTotal());

        /**
         *  The maximum number of connections that can remain idle in the pool, without extra ones being released.
         */
        jedisPoolConfig.setMaxIdle(poolConfig.getMaxIdle());

        /**
         *  The maximum number of milliseconds that the caller needs to wait when no connection is available.
         */
        jedisPoolConfig.setMaxWaitMillis(poolConfig.getMaxWaitMillis());

        /**
         *  The minimum number of connections that can remain idle in the pool, without extra ones being created.
         */
        jedisPoolConfig.setMinIdle(poolConfig.getMinIdle());

        /**
         *  The minimum amount of time an object may sit idle in the pool before it is eligible for eviction by the idle object evictor (if any).
         */
        jedisPoolConfig.setMinEvictableIdleTimeMillis(poolConfig.getMinEvictableIdleTimeMillis());

        /**
         * 	The number of objects to examine during each run of the idle object evictor thread (if any).
         */
        jedisPoolConfig.setNumTestsPerEvictionRun(poolConfig.getNumTestsPerEvictionRun());

        /**
         * Whether the connections will be validated by using the ping command before they are borrowed from the pool.
         * If the connection turns out to be invalid, it will be removed from the pool.
         */
        jedisPoolConfig.setTestOnBorrow(poolConfig.getTestOnBorrow());

        /**
         * Whether connections will be validated using the ping command before they are returned to the pool.
         * If the connection turns out to be invalid, it will be removed from the pool.
         */
        jedisPoolConfig.setTestOnReturn(poolConfig.getTestOnReturn());

        /**
         * 	Whether to enable the idle resource detection.
         */
        jedisPoolConfig.setTestWhileIdle(poolConfig.getTestWhileIdle());

        /**
         * 	The number of milliseconds to sleep between runs of the idle object evictor thread.
         */
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(poolConfig.getTimeBetweenEvictionRunsMillis());

        /**
         *  Whether the caller has to wait when the resource pool is exhausted. The following maxWaitMillis takes effect only when this value is true.
         */
        jedisPoolConfig.setBlockWhenExhausted(poolConfig.getBlockedWhenExhausted());

        return jedisPoolConfig;
    }

    @Override
    public CacheErrorHandler errorHandler() {
        log.info("Cache error handler");
        return new CustomCacheErrorHandler();
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {

        RedisStandaloneConfiguration redisStandaloneConfig = new RedisStandaloneConfiguration();
        redisStandaloneConfig.setDatabase(redisProperties.getDatabase());
        redisStandaloneConfig.setHostName(redisProperties.getHost());
        redisStandaloneConfig.setPort(redisProperties.getPort());
        redisStandaloneConfig.setPassword(RedisPassword.of(redisProperties.getPassword()));

        JedisClientConfiguration.JedisClientConfigurationBuilder jedisConfigurationBuilder = JedisClientConfiguration.builder();

        jedisConfigurationBuilder
                // Configure a connection timeout.
                .connectTimeout(Duration.ofMillis(redisProperties.getConnectionTimeout()))
                // Configure a read timeout.
                .readTimeout(Duration.ofMillis(redisProperties.getReadTimeout()))
                // Enable connection-pooling.
                .usePooling()
                .poolConfig(jedisPoolConfig());

        return new JedisConnectionFactory(redisStandaloneConfig, jedisConfigurationBuilder.build());
    }

    @Primary
    @Bean(name = ExpiryTimeConstant.Time.ONE_MIN)
    public CacheManager cacheManager1(RedisConnectionFactory redisConnectionFactory) {
        return buildCacheManager(redisConnectionFactory, ExpiryTimeConstant.EXPIRY.get(ExpiryTimeConstant.Time.ONE_MIN));
    }


    @Bean(name = ExpiryTimeConstant.Time.FIVE_MIN)
    public CacheManager cacheManager2(RedisConnectionFactory redisConnectionFactory) {
        return buildCacheManager(redisConnectionFactory, ExpiryTimeConstant.EXPIRY.get(ExpiryTimeConstant.Time.FIVE_MIN));
    }

    @Bean(name = ExpiryTimeConstant.Time.ONE_HOUR)
    public CacheManager cacheManager4(RedisConnectionFactory redisConnectionFactory) {
        return buildCacheManager(redisConnectionFactory, ExpiryTimeConstant.EXPIRY.get(ExpiryTimeConstant.Time.ONE_HOUR));
    }

    @Bean(name = ExpiryTimeConstant.Time.ETERNAL)
    public CacheManager cacheManager3(RedisConnectionFactory redisConnectionFactory) {
        return buildCacheManager(redisConnectionFactory, ExpiryTimeConstant.EXPIRY.get(ExpiryTimeConstant.Time.ETERNAL));
    }

    @Primary
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory,
                                                       @Qualifier("stringSerializer") RedisSerializer stringSerializer,
                                                       @Qualifier("jdkSerializer") RedisSerializer jdkSerializer) {
        RedisTemplate<String, Object> redisTemplate = new CustomRedisTemplate<String, Object>();

        redisTemplate.setStringSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setKeySerializer(stringSerializer);

        redisTemplate.setValueSerializer(jdkSerializer);
        redisTemplate.setHashValueSerializer(jdkSerializer);

        redisTemplate.setConnectionFactory(redisConnectionFactory);

        return redisTemplate;
    }

    @Bean(name = "redisTemplate2")
    public RedisTemplate<String, Object> redisTemplate2(RedisConnectionFactory redisConnectionFactory,
                                                        @Qualifier("stringSerializer") RedisSerializer stringSerializer,
                                                        @Qualifier("jdkSerializer") RedisSerializer jdkSerializer) {
        RedisTemplate<String, Object> redisTemplate = new CustomRedisTemplate<>();

        redisTemplate.setStringSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setKeySerializer(stringSerializer);

        redisTemplate.setValueSerializer(jdkSerializer);
        redisTemplate.setHashValueSerializer(jdkSerializer);

        redisTemplate.setConnectionFactory(redisConnectionFactory);

        return redisTemplate;
    }

    protected RedisCacheManager buildCacheManager(RedisConnectionFactory redisConnectionFactory, Duration duration) {

        //RedisCacheManager generator creation
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(
                        RedisCacheConfiguration.defaultCacheConfig()
                                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringSerializer()))
                                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer()))
                                .entryTtl(duration));
        return builder.build();
    }

    @Bean("stringSerializer")
    public RedisSerializer stringSerializer() {
        return new StringRedisSerializer();
    }

    @Bean("jdkSerializer")
    public RedisSerializer jdkSerializer() {
        return new JdkSerializationRedisSerializer(getClass().getClassLoader());
    }
}
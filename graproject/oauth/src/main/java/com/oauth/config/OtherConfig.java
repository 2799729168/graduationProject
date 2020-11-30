package com.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author pb
 */
@Configuration
public class OtherConfig {
    @Bean
    public JedisPool jedisPool(){
//        全部为默认
        return new JedisPool();
    }
}

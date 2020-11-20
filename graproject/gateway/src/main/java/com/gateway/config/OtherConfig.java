package com.gateway.config;

import com.gateway.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author pb
 */
@Configuration
public class OtherConfig {
    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }
    @Bean
    public JedisPool jedisPool(){
        JedisPool jedisPool = new JedisPool();
        return jedisPool;
    }
}

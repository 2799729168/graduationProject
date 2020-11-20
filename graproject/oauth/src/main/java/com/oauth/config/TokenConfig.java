package com.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.StandardStringSerializationStrategy;

import java.util.Collections;
import java.util.Map;

/**
 * @author pb
 */
@Configuration
public class TokenConfig {
    public static final String key = "test";
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    //    自定义token获取服务

    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
        services.setTokenStore(tokenStore());
        services.setSupportRefreshToken(true);
        services.setClientDetailsService(clientDetailsService);
//        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(converter()));
//        services.setTokenEnhancer(tokenEnhancerChain);
        return services;
    }

    //token保存策略

    @Bean
    public TokenStore tokenStore (){
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setAuthenticationKeyGenerator(oAuth2Authentication -> {
            Object details = oAuth2Authentication.getUserAuthentication().getDetails();
            Map<String,String> userDetails = (Map<String, String>) details;
            return userDetails.get("username");
        });
        return redisTokenStore;
    }

//    jwt形式的token转化器

//    @Bean
//    public JwtAccessTokenConverter converter(){
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(key);
//        return converter;
//    }
}

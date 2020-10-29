package com.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Collections;

/**
 * @author pb
 */
@Configuration
public class TokenConfig {
    public static final String key = "test";
    @Autowired
    private ClientDetailsService clientDetailsService;
    //    自定义token获取服务

    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
        services.setTokenStore(tokenStore());
        services.setSupportRefreshToken(true);
        services.setClientDetailsService(clientDetailsService);
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(converter()));
        services.setTokenEnhancer(tokenEnhancerChain);
        return services;
    }

    //token保存策略

    @Bean
    public TokenStore tokenStore (){
        return new JwtTokenStore(converter());
    }

//    jwt形式的token转化器

    @Bean
    public JwtAccessTokenConverter converter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(key);
        return converter;
    }
}

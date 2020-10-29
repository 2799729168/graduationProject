package com.oauth.config;

import com.oauth.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.token.DefaultToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.Collections;

/**
 * @author pb
 */
@EnableAuthorizationServer
@Configuration
public class OauthConfig extends AuthorizationServerConfigurerAdapter {
    public static final String key = "test";
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ClientDetailsService clientDetailsService;

    //指定客户端登录信息来源

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("c1")
        .secret(new BCryptPasswordEncoder().encode("test"))
        .resourceIds("r1")
        .authorizedGrantTypes("authorization_code","password","implicit","client_credentials")
        .scopes("all")
        .autoApprove(true)
        .redirectUris("http://www.baidu.com");
    }
//    令牌端点安全约束

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.allowFormAuthenticationForClients();
        oauthServer.checkTokenAccess("permitAll()");

    }
//    端点配置

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .approvalStore(approvalStore())
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices())
                .tokenServices(tokenServices());
    }

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
    //授权信息保存策略

    @Bean
    public ApprovalStore approvalStore() {
        return new InMemoryApprovalStore();
    }
    //授权码模式数据来源

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

}
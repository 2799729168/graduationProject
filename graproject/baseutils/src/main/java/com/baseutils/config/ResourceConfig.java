package com.baseutils.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author pb
 */
@Configuration
@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)

public class ResourceConfig extends ResourceServerConfigurerAdapter {
    @Value("${oauth.scope}")
    private String scope = "";
    public static final String key = "test";
//    public static final String ResourceId = "r1";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .tokenServices(tokenService())
//                .tokenStore(tokenStore())
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//指定不同请求方式访问资源所需要的权限，一般查询是read，其余是write。
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/**").access("#oauth2.hasAnyScope("+scope+")")
                .and()
                .headers().addHeaderWriter((request, response) -> {
            response.addHeader("Access-Control-Allow-Origin", "*");//允许跨域
            if (request.getMethod().equals("OPTIONS")) {//如果是跨域的预检请求，则原封不动向下传达请求头信息
                response.setHeader("Access-Control-Allow-Methods", request.getHeader("AccessControl-Request-Method"));
                response.setHeader("Access-Control-Allow-Headers", request.getHeader("AccessControl-Request-Headers"));
            }
        });

    }
//    token校验
    public ResourceServerTokenServices tokenService(){
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl("http://localhost:9000/oauth/check_token");
        services.setClientId("c1");
        services.setClientSecret("test");
        return services;
    }
//
//    //token保存策略
//
//    @Bean
//    public TokenStore tokenStore (){
//        return new JwtTokenStore(converter());
//    }
//
////    jwt形式的token转化器
//
//    @Bean
//    public JwtAccessTokenConverter converter(){
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(key);
//        return converter;
//    }
}

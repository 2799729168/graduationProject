package com.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.List;

/**
 * @author pb
 */
public class TokenFilter implements GlobalFilter, Ordered {
    @Autowired
    public JedisPool jedisPool;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest servletRequest = exchange.getRequest();
        ServerHttpRequest.Builder mutate = servletRequest.mutate();
        String str = servletRequest.getHeaders().get("Authorization").get(0);
        if(!StringUtils.isEmpty(str)){
            System.out.println(str);
        }else{
            MultiValueMap<String, HttpCookie> cookies = servletRequest.getCookies();
            List<HttpCookie> userName = cookies.get("username");
            Jedis resource = this.jedisPool.getResource();
            String userInfo = resource.get("auth_to_access:"+userName.get(0));
            System.out.println(userInfo);
            String[] s = userInfo.split("\\$");
            System.out.println(s[5]);
            mutate.header("Authorization","Bearer "+s[5]);
        }

        ServerHttpRequest build = mutate.build();
        return  chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

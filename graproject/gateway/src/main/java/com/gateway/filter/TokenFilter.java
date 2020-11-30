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
        String str = null;
        try {
            str = servletRequest.getHeaders().get("Authorization").get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!StringUtils.isEmpty(str)){
            System.out.println(str);
        }else{
            MultiValueMap<String, HttpCookie> cookies = servletRequest.getCookies();
            List<HttpCookie> userName = cookies.get("username");
            if(userName != null && userName.get(0) != null){
                Jedis resource = this.jedisPool.getResource();
                List<String> token = resource.hmget("access_token",userName.get(0).getValue());
                if(token!=null && token.get(0)!= null){
                    mutate.header("Authorization","Bearer "+token.get(0));
                }
            }
        }
        ServerHttpRequest build = mutate.build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

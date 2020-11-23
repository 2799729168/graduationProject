package com.oauth;

import com.google.common.primitives.Bytes;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author pb
 */
@SpringBootApplication
@MapperScan(basePackages = "com.oauth.mapper")
@EnableSwagger2
@EnableEurekaClient
@ServletComponentScan(basePackages = "com.oauth.filter")
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }

}

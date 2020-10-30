package com.pubutils;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author pb
 */
@SpringBootApplication
@MapperScan(basePackages = "com.pubutils.mapper")
@ComponentScans(value = {@ComponentScan(value = "com.baseutils")})
@EnableSwagger2
@EnableEurekaClient
public class PubUtilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PubUtilsApplication.class, args);
    }

}

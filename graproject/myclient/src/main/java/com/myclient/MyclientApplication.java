package com.myclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author pb
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
//扫描到通用包
@ComponentScans(value = {@ComponentScan(value = "com.baseutils")})
public class MyclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyclientApplication.class, args);
    }

}

package com.myclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author pb
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class MyclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyclientApplication.class, args);
    }

}

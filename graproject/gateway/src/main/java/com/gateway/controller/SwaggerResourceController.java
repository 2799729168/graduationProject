package com.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger.web.*;

import java.util.List;

/**
 * swagger聚合接口，三个接口都是swagger-ui.html需要访问的接口
 * @author pb
 */
@RestController
@RequestMapping("/swagger-resources")
public class SwaggerResourceController {
    private SwaggerResourcesProvider SwaggerDocConfig;

    @Autowired
    public SwaggerResourceController(SwaggerResourcesProvider SwaggerDocConfig) {
        this.SwaggerDocConfig = SwaggerDocConfig;
    }

    @RequestMapping(value = "/configuration/security")
    public ResponseEntity<SecurityConfiguration> securityConfiguration() {
        return new ResponseEntity<>(SecurityConfigurationBuilder.builder().build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/configuration/ui")
    public ResponseEntity<UiConfiguration> uiConfiguration() {
        return new ResponseEntity<>(UiConfigurationBuilder.builder().build(), HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<List<SwaggerResource>> swaggerResources() {
        return new ResponseEntity<>(SwaggerDocConfig.get(), HttpStatus.OK);
    }
}
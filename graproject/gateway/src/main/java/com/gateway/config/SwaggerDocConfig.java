package com.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@AllArgsConstructor
class SwaggerDocConfig implements SwaggerResourcesProvider {
    public static final String API_URI = "/v2/api-docs";
    public static final String EUREKA_SUB_PRIX = "ReactiveCompositeDiscoveryClient_";
    private final DiscoveryClientRouteDefinitionLocator routeLocator;
//    @Override
//    public List<SwaggerResource> get() {
//        List<SwaggerResource> resources = new ArrayList<>();
//        List<String> routes = new ArrayList<>();
//        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
//        gatewayProperties.getRoutes().stream().filter(routeDefinition ->
//                routes.contains(routeDefinition.getId()))
//                .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
//                        .filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()))
//                        .filter(predicateDefinition -> !"pigx-auth".equalsIgnoreCase(routeDefinition.getId()))
//                        .forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
//                                predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
//                                        .replace("/**", API_URI)))));
//        return resources;
//    }

    @Override
    public List<SwaggerResource> get() {

        List<SwaggerResource> resources = new ArrayList<>();

        List<String> routes = new ArrayList<>();

        //从DiscoveryClientRouteDefinitionLocator 中取出routes，构造成swaggerResource
        routeLocator.getRouteDefinitions().subscribe(routeDefinition -> {
            String name = routeDefinition.getId().substring(EUREKA_SUB_PRIX.length());
            if(!"spring-cloud-gateway".equalsIgnoreCase(name)){
                resources.add(swaggerResource(name,routeDefinition.getPredicates().get(0).getArgs().get("pattern").replace("/**", API_URI)));
            }
        });

        return resources;

    }


    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

}
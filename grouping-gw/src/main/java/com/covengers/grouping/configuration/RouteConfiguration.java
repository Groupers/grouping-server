package com.covengers.grouping.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(r -> r.path("/v2/api-docs/api")
                        .filters(f -> f.rewritePath("/v2/api-docs/api", "/v2/api-docs"))
                        .uri("lb://grouping-api")
                        .id("grouping-api:docs"))
                .route(r -> r.path(
                        "/v1/group/**",
                        "/v1/keywords/**",
                        "/v1/sign/**",
                        "/v1/users/**")
                        .uri("lb://grouping-api")
                        .id("grouping-api:api"))
                .route(r -> r.path("/v2/api-docs/chat")
                        .filters(f -> f.rewritePath("/v2/api-docs/chat", "/v2/api-docs"))
                        .uri("lb://grouping-chat")
                        .id("grouping-chat:docs"))
                .route(r -> r.path("/v1/chat/**")
                        .uri("lb://grouping-chat")
                        .id("grouping-chat:api"))
                .route(r -> r.path("/websocket/chat")
                        .filters(f -> f.rewritePath("/websocket/chat", "/chat"))
                        .uri("lb://grouping-chat")
                        .id("grouping-chat:ws-connect"))
                .build();
    }
}

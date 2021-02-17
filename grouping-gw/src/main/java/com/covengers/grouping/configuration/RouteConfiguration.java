package com.covengers.grouping.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {

    @Value("${grouping.version}")
    public String groupingVersion;

    @Value("${grouping.url.api}")
    public String groupingUrlApi;

    @Value("${grouping.url.chat}")
    public String groupingUrlChat;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(r -> r.path("/" + groupingVersion + "/group/**")
                        .uri(groupingUrlApi)
                        .id("grouping-api:group"))
                .route(r -> r.path("/" + groupingVersion + "/keywords/**")
                        .uri(groupingUrlApi)
                        .id("grouping-api:keyword"))
                .route(r -> r.path("/" + groupingVersion + "/sign/**")
                        .uri(groupingUrlApi)
                        .id("grouping-api:sign"))
                .route(r -> r.path("/" + groupingVersion + "/users/**")
                        .uri(groupingUrlApi)
                        .id("grouping-api:user"))
                .route(r -> r.path("/" + groupingVersion + "/chat/**")
                        .uri(groupingUrlChat)
                        .id("grouping-chat:chat"))
                .route(r -> r.path("/websocket/**")
                        .filters(f -> f.rewritePath("/websocket/(?.*)", "/${remains}"))
                        .uri(groupingUrlChat)
                        .id("grouping-chat:ws"))
                .build();
    }
}

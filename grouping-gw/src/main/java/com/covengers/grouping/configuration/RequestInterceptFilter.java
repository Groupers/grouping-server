package com.covengers.grouping.configuration;

import com.covengers.grouping.constant.RequestHeaders;
import com.covengers.grouping.vo.UserPrincipal;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class RequestInterceptFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        final SecurityContext securityContext = SecurityContextHolder.getContext();

        if (Objects.isNull(securityContext)
                || securityContext.getAuthentication() == null
                || !securityContext.getAuthentication().isAuthenticated()) {
            return chain.filter(exchange);
        }

        final UserPrincipal principal = (UserPrincipal) securityContext.getAuthentication().getPrincipal();

        ServerHttpRequest mutatedRequest =
                exchange.getRequest().mutate().header(
                        RequestHeaders.GROUPING_USER_ID,
                        principal.getGroupingUserId().toString()).build();
        ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
        return chain.filter(mutatedExchange);
    }
}

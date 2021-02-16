package com.covengers.grouping.configuration;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.covengers.grouping.constant.RequestHeaders;
import com.covengers.grouping.vo.UserPrincipal;

import reactor.core.publisher.Mono;

public class RequestInterceptFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return ReactiveSecurityContextHolder.getContext()
                                            .map(SecurityContext::getAuthentication)
                                            .filter(Authentication::isAuthenticated)
                                            .map(authentication -> {

                                                final UserPrincipal principal =
                                                        (UserPrincipal) authentication.getPrincipal();
                                                final ServerHttpRequest mutatedRequest =
                                                        exchange.getRequest().mutate().header(
                                                                RequestHeaders.GROUPING_USER_ID,
                                                                principal.getGroupingUserId().toString())
                                                                .build();

                                                return exchange.mutate().request(mutatedRequest).build();
                                            })
                                            .switchIfEmpty(Mono.just(exchange))
                                            .flatMap(chain::filter);

    }

}

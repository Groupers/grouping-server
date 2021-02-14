package com.covengers.grouping.component;

import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.exception.CommonException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        return Mono.error(new CommonException(ResponseCode.UNAUTHORIZED_ERROR));
    }

}

package com.covengers.grouping.configuration;

import com.covengers.grouping.component.JwtTokenProvider;
import com.covengers.grouping.service.GroupingUserRepositoryDecorator;
import com.covengers.grouping.vo.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtTokenProvider tokenProvider;

    private final GroupingUserRepositoryDecorator groupingUserRepository;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        final String jwt = authentication.getCredentials().toString();

        try {
            if (!StringUtils.hasText(jwt) || !tokenProvider.validateToken(jwt)) {
                return Mono.empty();
            }
            final Long groupingUserId = tokenProvider.getUserIdFromJwt(jwt);

            final UserPrincipal userPrincipal = groupingUserRepository.loadUserById(groupingUserId);

            final UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(userPrincipal, null,
                            userPrincipal.getAuthorities());

            return Mono.just(token);

        } catch (Exception e) {
            return Mono.empty();
        }
    }

}

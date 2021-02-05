package com.covengers.grouping.adapter.api;

import java.util.Objects;

import com.covengers.grouping.vo.UserPrincipal;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.covengers.grouping.constant.RequestHeaders;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GroupingApiRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {

        final SecurityContext securityContext = SecurityContextHolder.getContext();
        if (Objects.isNull(securityContext)
                || !securityContext.getAuthentication().isAuthenticated()
                || securityContext.getAuthentication() instanceof AnonymousAuthenticationToken) {
            return;
        }

        final UserPrincipal principal = (UserPrincipal) securityContext.getAuthentication().getPrincipal();

        template.header(RequestHeaders.GROUPING_USER_ID,
                principal.getGroupingUserId().toString());

    }
}

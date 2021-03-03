package com.covengers.grouping.configuration;

import com.covengers.grouping.component.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationManager authenticationManager;

    private final SecurityContextRepository securityContextRepository;

    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    private static final String[] EXCLUDE_GET_REQUEST_PATH = {
            "/v1/sign/email",
            "/v1/sign/phone-number",
    };

    private static final String[] EXCLUDE_POST_REQUEST_PATH = {
            "/v1/sign/complete",
            "/v1/sign/login/email",
            "/v1/sign/login/phone-number",
    };

    private static final String[] EXCLUDE_SWAGGER_REQUEST_PATH = {
            "/v2/api-docs/**", "/swagger-resources/configuration/ui",
            "/swagger-resources","/swagger-resources/configuration/security",
            "/swagger-ui/","/webjars/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        return http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers("/", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg",
                        "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js")
                .permitAll()
                .pathMatchers(EXCLUDE_POST_REQUEST_PATH)
                .permitAll()
                .pathMatchers(HttpMethod.GET, EXCLUDE_GET_REQUEST_PATH)
                .permitAll()
                .pathMatchers(EXCLUDE_SWAGGER_REQUEST_PATH)
                .permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .addFilterAfter(new RequestInterceptFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();

    }
}

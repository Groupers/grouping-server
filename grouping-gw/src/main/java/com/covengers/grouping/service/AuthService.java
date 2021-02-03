package com.covengers.grouping.service;

import com.covengers.grouping.adapter.chat.GroupingChatClient;
import com.covengers.grouping.dto.CreateGroupCompleteRequestDto;
import com.covengers.grouping.dto.CreateGroupRequestDto;
import com.covengers.grouping.dto.GroupDto;
import com.covengers.grouping.vo.CreateGroupRequestVo;
import com.covengers.grouping.vo.GroupVo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.covengers.grouping.adapter.api.GroupingApiClient;
import com.covengers.grouping.adapter.api.dto.SignUpCompleteRequestDto;
import com.covengers.grouping.component.JwtTokenProvider;
import com.covengers.grouping.dto.GroupingUserDto;
import com.covengers.grouping.vo.JwtTokenVo;
import com.covengers.grouping.vo.SignUpRequestVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final GroupingApiClient groupingApiClient;

    private final GroupingChatClient groupingChatClient;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JwtTokenVo completeSignUp(SignUpRequestVo requestVo) {

        final SignUpCompleteRequestDto signUpCompleteRequestDto =
                SignUpCompleteRequestDto.of(requestVo, passwordEncoder.encode(requestVo.getPassword()));

        final GroupingUserDto groupingUserDto =
                groupingApiClient.completeSignUp(signUpCompleteRequestDto).getData();

        final UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(requestVo.getEmail(),
                                                        requestVo.getPassword());

        final Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwtToken = tokenProvider.generateToken(authentication);

        return JwtTokenVo.builder()
                         .accessToken(jwtToken)
                         .build();

    }

    @Transactional
    public GroupVo createGroup(CreateGroupRequestVo requestVo) {

        final GroupDto groupDto = groupingChatClient.createGroup(CreateGroupCompleteRequestDto.of(requestVo)).getData();

        return groupDto.toVo();
    }
}

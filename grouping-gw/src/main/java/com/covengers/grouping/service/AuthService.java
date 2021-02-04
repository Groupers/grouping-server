package com.covengers.grouping.service;

import com.covengers.grouping.adapter.api.dto.*;
import com.covengers.grouping.dto.RecommendGroupDto;
import com.covengers.grouping.dto.SearchHistoryListResultDto;
import com.covengers.grouping.vo.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.covengers.grouping.adapter.api.GroupingApiClient;
import com.covengers.grouping.component.JwtTokenProvider;
import com.covengers.grouping.dto.GroupResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final GroupingApiClient groupingApiClient;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    public SignUpCheckEmailResponseVo checkSignUpEmail(String email) {

        final SignUpCheckEmailResponseDto signUpCheckEmailResponseDto =
                groupingApiClient.checkSignUpEmail(email).getData();

        return SignUpCheckEmailResponseVo.builder()
                .isEmailAvailable(signUpCheckEmailResponseDto.isEmailAvailable())
                .build();

    }

    public SignUpCheckPhoneNumberResponseVo checkSignUpPhoneNumber(String phoneNumber) {

        final SignUpCheckPhoneNumberResponseDto signUpCheckPhoneNumberResponseDto =
                groupingApiClient.checkSignUpPhoneNumber(phoneNumber).getData();

        return SignUpCheckPhoneNumberResponseVo.builder()
                .isPhoneNumberAvailable(
                        signUpCheckPhoneNumberResponseDto
                                .isPhoneNumberAvailable())
                .build();
    }

    public JwtTokenVo completeSignUp(SignUpRequestVo signUpRequestVo) {

        final SignUpCompleteRequestDto signUpCompleteRequestDto =
                SignUpCompleteRequestDto.of(signUpRequestVo,
                        passwordEncoder.encode(signUpRequestVo.getPassword()));

        groupingApiClient.completeSignUp(signUpCompleteRequestDto);

        return generateToken(signUpRequestVo.getEmail(), signUpRequestVo.getPassword());

    }

    public JwtTokenVo signInWithEmail(SignInWithEmailRequestVo signInWithEmailRequestVo) {

        final SignInWithEmailRequestDto signInWithEmailRequestDto =
                SignInWithEmailRequestDto.of(signInWithEmailRequestVo);

        groupingApiClient.signInWithEmail(signInWithEmailRequestDto);

        return generateToken(signInWithEmailRequestVo.getEmail(), signInWithEmailRequestVo.getPassword());
    }

    public JwtTokenVo signInWithPhoneNumber(SignInWithPhoneNumberRequestVo signInWithPhoneNumberRequestVo) {

        final SignInWithPhoneNumberRequestDto signInWithPhoneNumberRequestDto =
                SignInWithPhoneNumberRequestDto.of(signInWithPhoneNumberRequestVo);

        groupingApiClient.signInWithPhoneNumber(signInWithPhoneNumberRequestDto);

        return generateToken(signInWithPhoneNumberRequestVo.getPhoneNumber(),
                signInWithPhoneNumberRequestVo.getPassword());
    }

    private JwtTokenVo generateToken(String phoneOrEmail, String password) {

        final UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(phoneOrEmail, password);

        final Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwtToken = tokenProvider.generateToken(authentication);

        return JwtTokenVo.builder()
                .accessToken(jwtToken)
                .build();
    }

    public GroupResponseVo createGroup(CreateGroupRequestVo requestVo) {

        final com.covengers.grouping.adapter.api.dto.GroupResponseDto groupResponseDto = groupingApiClient.createGroup(CreateGroupCompleteRequestDto.of(requestVo))
                .getData();

        return groupResponseDto.toVo();
    }

    public GroupResponseVo uploadGroupImage(MultipartFile imageFile,
                                            final Long groupId) {
        final GroupResponseDto groupResponseDto = groupingApiClient.uploadGroupImage(imageFile, groupId).getData();

        return groupResponseDto.toVo();
    }

    public RecommendGroupVo recommendGroup(Long groupingUserId, String keyword) {

        final RecommendGroupDto recommendGroupDto = groupingApiClient.recommendGroup(groupingUserId, keyword).getData();

        return recommendGroupDto.toVo();
    }

    public SearchHistoryListResultVo getSearchHistoryList(Long groupingUserId) {

        final SearchHistoryListResultDto searchHistoryListResultDto = groupingApiClient.getSearchHistoryList(groupingUserId).getData();

        return searchHistoryListResultDto.toVo();
    }
}

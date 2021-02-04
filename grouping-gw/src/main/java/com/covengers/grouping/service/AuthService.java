package com.covengers.grouping.service;

import com.covengers.grouping.adapter.api.dto.*;
import com.covengers.grouping.adapter.api.dto.GroupResponseDto;
import com.covengers.grouping.adapter.api.dto.SignInWithEmailRequestDto;
import com.covengers.grouping.adapter.api.dto.SignInWithPhoneNumberRequestDto;
import com.covengers.grouping.adapter.api.dto.SignUpCheckEmailResponseDto;
import com.covengers.grouping.adapter.api.dto.SignUpCheckPhoneNumberResponseDto;
import com.covengers.grouping.adapter.chat.GroupingChatClient;
import com.covengers.grouping.adapter.chat.domain.ChatMessage;
import com.covengers.grouping.adapter.chat.dto.ChatRoomResponseDto;
import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.vo.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.covengers.grouping.adapter.api.GroupingApiClient;
import com.covengers.grouping.component.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final GroupingApiClient groupingApiClient;

    private final GroupingChatClient groupingChatClient;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

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

    public RecommendGroupResponseVo recommendGroup(Long groupingUserId, String keyword) {

        final RecommendGroupResponseDto recommendGroupResponseDto = groupingApiClient.recommendGroup(groupingUserId, keyword).getData();

        return recommendGroupResponseDto.toVo();
    }

    public SearchHistoryListResponseVo getSearchHistoryList(Long groupingUserId) {

        final SearchHistoryListResponseDto searchHistoryListResponseDto = groupingApiClient.getSearchHistoryList(groupingUserId).getData();

        return searchHistoryListResponseDto.toVo();
    }

    public SearchTrendsListResponseVo getSearchTrendsList() {

        final SearchTrendsListResponseDto searchTrendsListResponseDto = groupingApiClient.getSearchTrendsList().getData();

        return searchTrendsListResponseDto.toVo();
    }

    public GroupListResponseVo getGroupList(Long groupingUserId) {

        final GroupListResponseDto groupListResponseDto = groupingApiClient.getGroupList(groupingUserId).getData();

        return groupListResponseDto.toVo();
    }
    public FriendListResponseVo getFriendList(Long groupingUserId) {

        final FriendListResponseDto friendListResponseDto = groupingApiClient.getFriendList(groupingUserId).getData();

        return friendListResponseDto.toVo();
    }

    public GroupingUserResponseVo checkUserWithEmailAndPhoneNumber(
            String email, String phoneNumber) {

        final GroupingUserResponseDto groupingUserResponseDto = groupingApiClient.checkUserWithEmailAndPhoneNumber(email, phoneNumber).getData();

        return groupingUserResponseDto.toVo();
    }

    public JwtTokenVo resetPassword(
            Long groupingUserId, ResetPasswordRequestVo resetPasswordRequestVo) {

        final ResetPasswordCompleteRequestDto resetPasswordCompleteRequestDto =
                ResetPasswordCompleteRequestDto.of(passwordEncoder.encode(resetPasswordRequestVo.getPassword()));

        Void data = groupingApiClient.resetPassword(groupingUserId, resetPasswordCompleteRequestDto).getData();

        return generateToken("temp", resetPasswordRequestVo.getPassword());
    }
    public ChatRoomResponseVo createChatRoom(@RequestParam String title) {
        final ChatRoomResponseDto chatRoomResponseDto = groupingChatClient.createChatRoom(title).getData();
        return chatRoomResponseDto.toVo();
    }

    @PostMapping("/room/enter")
    public ChatRoomResponseVo enterChatRoom(@RequestParam Long chatRoomId) {
        ChatRoomResponseDto chatRoomResponseDto = groupingChatClient.enterChatRoom(chatRoomId).getData();
        return chatRoomResponseDto.toVo();
    }

    @MessageMapping("/chat/message")
    public void sendMessage(ChatMessage chatMessage) {
        groupingChatClient.sendMessage(chatMessage);
    }

}

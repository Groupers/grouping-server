package com.covengers.grouping.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.covengers.grouping.adapter.api.GroupingApiClient;
import com.covengers.grouping.adapter.api.dto.CreateGroupCompleteRequestDto;
import com.covengers.grouping.adapter.api.dto.FriendListResponseDto;
import com.covengers.grouping.adapter.api.dto.GroupListResponseDto;
import com.covengers.grouping.adapter.api.dto.GroupResponseDto;
import com.covengers.grouping.adapter.api.dto.GroupingUserResponseDto;
import com.covengers.grouping.adapter.api.dto.RecommendGroupResponseDto;
import com.covengers.grouping.adapter.api.dto.ResetPasswordCompleteRequestDto;
import com.covengers.grouping.adapter.api.dto.SearchHistoryListResponseDto;
import com.covengers.grouping.adapter.api.dto.SearchTrendsListResponseDto;
import com.covengers.grouping.adapter.api.dto.SignInWithEmailRequestDto;
import com.covengers.grouping.adapter.api.dto.SignInWithPhoneNumberRequestDto;
import com.covengers.grouping.adapter.api.dto.SignUpCheckEmailResponseDto;
import com.covengers.grouping.adapter.api.dto.SignUpCheckPhoneNumberResponseDto;
import com.covengers.grouping.adapter.api.dto.SignUpCompleteRequestDto;
import com.covengers.grouping.adapter.chat.GroupingChatClient;
import com.covengers.grouping.adapter.chat.dto.ChatRoomResponseDto;
import com.covengers.grouping.component.JwtTokenProvider;
import com.covengers.grouping.vo.ChatRoomResponseVo;
import com.covengers.grouping.vo.CreateGroupRequestVo;
import com.covengers.grouping.vo.FriendListResponseVo;
import com.covengers.grouping.vo.GroupListResponseVo;
import com.covengers.grouping.vo.GroupResponseVo;
import com.covengers.grouping.vo.GroupingUserResponseVo;
import com.covengers.grouping.vo.JwtTokenVo;
import com.covengers.grouping.vo.RecommendGroupResponseVo;
import com.covengers.grouping.vo.ResetPasswordRequestVo;
import com.covengers.grouping.vo.SearchHistoryListResponseVo;
import com.covengers.grouping.vo.SearchTrendsListResponseVo;
import com.covengers.grouping.vo.SignInWithEmailRequestVo;
import com.covengers.grouping.vo.SignInWithPhoneNumberRequestVo;
import com.covengers.grouping.vo.SignUpCheckEmailResponseVo;
import com.covengers.grouping.vo.SignUpCheckPhoneNumberResponseVo;
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

        final GroupResponseDto groupResponseDto = groupingApiClient.createGroup(
                CreateGroupCompleteRequestDto.of(requestVo))
                                                                   .getData();

        return groupResponseDto.toVo();
    }

    public GroupResponseVo uploadGroupImage(MultipartFile imageFile,
                                            final Long groupId) {
        final GroupResponseDto groupResponseDto = groupingApiClient.uploadGroupImage(imageFile, groupId)
                                                                   .getData();

        return groupResponseDto.toVo();
    }

    public RecommendGroupResponseVo recommendGroup(String keyword) {

        final RecommendGroupResponseDto recommendGroupResponseDto =
                groupingApiClient.recommendGroup(keyword).getData();

        return recommendGroupResponseDto.toVo();
    }

    public SearchHistoryListResponseVo getSearchHistoryList() {

        final SearchHistoryListResponseDto searchHistoryListResponseDto =
                groupingApiClient.getSearchHistoryList().getData();

        return searchHistoryListResponseDto.toVo();
    }

    public SearchTrendsListResponseVo getSearchTrendsList() {

        final SearchTrendsListResponseDto searchTrendsListResponseDto =
                groupingApiClient.getSearchTrendsList().getData();

        return searchTrendsListResponseDto.toVo();
    }

    public GroupListResponseVo getGroupList() {

        final GroupListResponseDto groupListResponseDto = groupingApiClient.getGroupList()
                                                                           .getData();

        return groupListResponseDto.toVo();
    }

    public FriendListResponseVo getFriendList() {

        final FriendListResponseDto friendListResponseDto = groupingApiClient.getFriendList()
                                                                             .getData();
        return friendListResponseDto.toVo();
    }

    public GroupingUserResponseVo checkUserWithEmailAndPhoneNumber(
            String email, String phoneNumber) {

        final GroupingUserResponseDto groupingUserResponseDto =
                groupingApiClient.checkUserWithEmailAndPhoneNumber(email, phoneNumber).getData();

        return groupingUserResponseDto.toVo();
    }

    public void resetPassword(
            Long groupingUserId, ResetPasswordRequestVo resetPasswordRequestVo) {

        final ResetPasswordCompleteRequestDto resetPasswordCompleteRequestDto =
                ResetPasswordCompleteRequestDto.of(
                        passwordEncoder.encode(resetPasswordRequestVo.getPassword()));

        groupingApiClient.resetPassword(groupingUserId, resetPasswordCompleteRequestDto);

    }

    public ChatRoomResponseVo createChatRoom(String title) {
        final ChatRoomResponseDto chatRoomResponseDto = groupingChatClient.createChatRoom(title).getData();
        return chatRoomResponseDto.toVo();
    }

    public ChatRoomResponseVo enterChatRoom(Long chatRoomId) {
        final ChatRoomResponseDto chatRoomResponseDto = groupingChatClient.enterChatRoom(chatRoomId).getData();
        return chatRoomResponseDto.toVo();
    }

}

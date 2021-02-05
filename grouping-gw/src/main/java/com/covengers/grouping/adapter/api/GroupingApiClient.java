package com.covengers.grouping.adapter.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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
import com.covengers.grouping.configuration.FeignConfiguration;

@FeignClient(value = "grouping-api",
        url = "${grouping.url.api}",
        configuration = {
                FeignConfiguration.class,
                GroupingApiRequestInterceptor.class,
        })
@RequestMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public interface GroupingApiClient {

    @GetMapping(path = "/sign/email")
    GroupingApiAdapterResponse<SignUpCheckEmailResponseDto> checkSignUpEmail(
            @RequestParam("email") String email);

    @GetMapping(path = "/sign/phone-number")
    GroupingApiAdapterResponse<SignUpCheckPhoneNumberResponseDto> checkSignUpPhoneNumber(
            @RequestParam("phone-number") String phoneNumber);

    @PostMapping(path = "/sign/complete")
    GroupingApiAdapterResponse<Void> completeSignUp(
            @RequestBody SignUpCompleteRequestDto signUpCompleteRequestDto);

    @PostMapping(path = "/sign/login/email")
    GroupingApiAdapterResponse<Void> signInWithEmail(
            @RequestBody SignInWithEmailRequestDto signInWithEmailRequestDto);

    @PostMapping(path = "/sign/login/phone-number")
    GroupingApiAdapterResponse<Void> signInWithPhoneNumber(
            @RequestBody SignInWithPhoneNumberRequestDto signInWithPhoneNumberRequestDto);

    @PostMapping(path = "/group")
    GroupingApiAdapterResponse<GroupResponseDto> createGroup(@RequestBody CreateGroupCompleteRequestDto dto);

    @PostMapping(path = "/group/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    GroupingApiAdapterResponse<GroupResponseDto> uploadGroupImage(
            @RequestPart MultipartFile imageFile,
            @RequestParam Long groupId
    );

    @GetMapping(path = "/group/keyword")
    GroupingApiAdapterResponse<RecommendGroupResponseDto> recommendGroup(@RequestParam String keyword);

    @GetMapping("/keywords/search/history")
    GroupingApiAdapterResponse<SearchHistoryListResponseDto> getSearchHistoryList();

    @GetMapping("/keywords/search/trends")
    GroupingApiAdapterResponse<SearchTrendsListResponseDto> getSearchTrendsList();

    @GetMapping("/users/groups")
    GroupingApiAdapterResponse<GroupListResponseDto> getGroupList();

    @GetMapping("/users/friends")
    GroupingApiAdapterResponse<FriendListResponseDto> getFriendList();

    @GetMapping("/users")
    GroupingApiAdapterResponse<GroupingUserResponseDto> checkUserWithEmailAndPhoneNumber(
            @RequestParam String email, @RequestParam String phoneNumber);

    @PutMapping("/users/password")
    GroupingApiAdapterResponse<Void> resetPassword(
            @RequestParam Long groupingUserId, @RequestBody ResetPasswordCompleteRequestDto requestDto);
}

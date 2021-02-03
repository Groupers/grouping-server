package com.covengers.grouping.adapter.api;

import com.covengers.grouping.adapter.api.dto.CreateGroupCompleteRequestDto;
import com.covengers.grouping.dto.GroupDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        })
@RequestMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public interface GroupingApiClient {

    @GetMapping(path = "/sign/email")
    GroupingApiAdapterResponse<SignUpCheckEmailResponseDto> checkSignUpEmail(@RequestParam("email") String email);

    @GetMapping(path = "/sign/phone-number")
    GroupingApiAdapterResponse<SignUpCheckPhoneNumberResponseDto> checkSignUpPhoneNumber(
            @RequestParam("phone-number") String phoneNumber);

    @PostMapping(path = "/sign/complete")
    GroupingApiAdapterResponse<Void> completeSignUp(
            @RequestBody SignUpCompleteRequestDto signUpCompleteRequestDto);

    @PostMapping(path = "/group")
    GroupingApiAdapterResponse<GroupDto> createGroup(@RequestBody CreateGroupCompleteRequestDto dto);

    @PostMapping(path = "/sign/login/email")
    GroupingApiAdapterResponse<Void> signInWithEmail(
            @RequestBody SignInWithEmailRequestDto signInWithEmailRequestDto);

    @PostMapping(path = "/sign/login/phone-number")
    GroupingApiAdapterResponse<Void> signInWithPhoneNumber(
            @RequestBody SignInWithPhoneNumberRequestDto signInWithPhoneNumberRequestDto);
}

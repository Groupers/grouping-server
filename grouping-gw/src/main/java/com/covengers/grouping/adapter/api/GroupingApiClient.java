package com.covengers.grouping.adapter.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.covengers.grouping.adapter.api.dto.SignUpCompleteRequestDto;
import com.covengers.grouping.configuration.FeignConfiguration;
import com.covengers.grouping.dto.GroupingUserDto;

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

    @PostMapping(path = "/sign/complete")
    GroupingApiAdapterResponse<GroupingUserDto> completeSignUp(@RequestBody SignUpCompleteRequestDto dto);
}

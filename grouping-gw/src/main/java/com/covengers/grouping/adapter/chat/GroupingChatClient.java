package com.covengers.grouping.adapter.chat;

import com.covengers.grouping.adapter.api.GroupingApiAdapterResponse;
import com.covengers.grouping.adapter.api.dto.SignUpCompleteRequestDto;
import com.covengers.grouping.dto.CreateGroupCompleteRequestDto;
import com.covengers.grouping.dto.GroupDto;
import com.covengers.grouping.dto.GroupingUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.covengers.grouping.configuration.FeignConfiguration;

@FeignClient(value = "grouping-chat",
        url = "${grouping.url.chat}",
        configuration = {
                FeignConfiguration.class,
        })
@RequestMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public interface GroupingChatClient {

        @PostMapping(path = "/group")
        GroupingChatAdapterResponse<GroupDto> createGroup(@RequestBody CreateGroupCompleteRequestDto dto);
}

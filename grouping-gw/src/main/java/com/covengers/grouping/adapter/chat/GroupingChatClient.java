package com.covengers.grouping.adapter.chat;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
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

}

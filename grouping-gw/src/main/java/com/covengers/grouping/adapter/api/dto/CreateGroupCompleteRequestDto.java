package com.covengers.grouping.adapter.api.dto;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.vo.CreateGroupRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class CreateGroupCompleteRequestDto {
    private final String accessToken;
    private final String title;
    private final Boolean isHidden;
    private final Integer minUserAge;
    private final Integer maxUserAge;
    private final Gender availableGender;
    private final String description;
    private final Long pointX;
    private final Long pointY;
    private final String pointDescription;
    private final Long representGroupingUserId;
    private final List<String> hashtagList;

    public static CreateGroupCompleteRequestDto of(CreateGroupRequestVo vo) {
        return builder()
                .accessToken(vo.getAccessToken())
                .title(vo.getTitle())
                .isHidden(vo.getIsHidden())
                .minUserAge(vo.getMinUserAge())
                .maxUserAge(vo.getMaxUserAge())
                .availableGender(vo.getAvailableGender())
                .description(vo.getDescription())
                .pointX(vo.getPointX())
                .pointY(vo.getPointY())
                .pointDescription(vo.getPointDescription())
                .representGroupingUserId(vo.getRepresentGroupingUserId())
                .hashtagList(vo.getHashtagList())
                .build();
    }
}

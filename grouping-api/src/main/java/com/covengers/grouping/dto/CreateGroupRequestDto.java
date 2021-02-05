package com.covengers.grouping.dto;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.vo.CreateGroupRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class CreateGroupRequestDto {
    private final String title;
    private final Boolean isHidden;
    private final Integer minUserAge;
    private final Integer maxUserAge;
    private final Gender availableGender;
    private final String description;
    private final Long pointX;
    private final Long pointY;
    private final String pointDescription;
    private final List<String> hashtagList;

    public CreateGroupRequestVo toVo(Long representGroupingUserId) {
        return CreateGroupRequestVo.builder()
                .title(title)
                .isHidden(isHidden)
                .minUserAge(minUserAge)
                .maxUserAge(maxUserAge)
                .availableGender(availableGender)
                .description(description)
                .pointX(pointX)
                .pointY(pointY)
                .pointDescription(pointDescription)
                .representGroupingUserId(representGroupingUserId)
                .hashtagList(hashtagList)
                .build();
    }
}

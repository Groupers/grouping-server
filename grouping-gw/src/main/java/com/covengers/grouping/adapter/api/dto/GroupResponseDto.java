package com.covengers.grouping.adapter.api.dto;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.vo.GroupResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@Getter
@Builder
@ToString
public class GroupResponseDto {
    private final Long id;
    private final String title;
    private final Boolean isHidden;
    private final Integer minUserAge;
    private final Integer maxUserAge;
    private final Gender availableGender;
    private final String description;
    private final Long pointX;
    private final Long pointY;
    private final String pointDescription;
    private final Optional<String> representGroupImage;

    public GroupResponseVo toVo() {
        return GroupResponseVo.builder()
                .id(id)
                .title(title)
                .isHidden(getIsHidden())
                .maxUserAge(maxUserAge)
                .minUserAge(minUserAge)
                .availableGender(availableGender)
                .description(description)
                .pointX(pointX)
                .pointY(pointY)
                .pointDescription(description)
                .representGroupImage(representGroupImage)
                .build();
    }
}

package com.covengers.grouping.dto;

import java.util.Optional;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.vo.GroupResponseVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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

    public static GroupResponseDto of(GroupResponseVo vo) {
        return builder()
                .id(vo.getId())
                .title(vo.getTitle())
                .isHidden(vo.getIsHidden())
                .maxUserAge(vo.getMaxUserAge())
                .minUserAge(vo.getMinUserAge())
                .availableGender(vo.getAvailableGender())
                .description(vo.getDescription())
                .pointX(vo.getPointX())
                .pointY(vo.getPointY())
                .pointDescription(vo.getPointDescription())
                .representGroupImage(vo.getRepresentGroupImage())
                .build();
    }

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

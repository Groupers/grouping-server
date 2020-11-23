package com.covengers.grouping.dto;

import java.util.List;
import java.util.Optional;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.vo.GroupVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class GroupDto {
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

    public static GroupDto of(GroupVo vo) {
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

}

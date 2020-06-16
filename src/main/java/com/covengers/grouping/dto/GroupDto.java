package com.covengers.grouping.dto;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.dto.vo.EnrollGroupRequestVo;
import com.covengers.grouping.dto.vo.GroupVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class GroupDto {
    private Long id;
    private String title;
    private Integer maxUserNumber;
    private Integer minUserAge;
    private Integer maxUserAge;
    private Gender availableGender;
    private String description;
    private Long pointX;
    private Long pointY;
    private String pointDescription;

    public static GroupDto of(GroupVo vo){
        return builder()
                .id(vo.getId())
                .title(vo.getTitle())
                .maxUserNumber(vo.getMaxUserNumber())
                .maxUserAge(vo.getMaxUserAge())
                .minUserAge(vo.getMinUserAge())
                .availableGender(vo.getAvailableGender())
                .description(vo.getDescription())
                .pointX(vo.getPointX())
                .pointY(vo.getPointY())
                .pointDescription(vo.getPointDescription())
                .build();
    }
}
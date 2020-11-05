package com.covengers.grouping.dto;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.vo.GroupInfoVo;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GroupInfoDto {
    private Long id;
    private String title;
    private Boolean isHidden;
    private Integer minUserAge;
    private Integer maxUserAge;
    private Gender availableGender;
    private String description;
    private Long pointX;
    private Long pointY;
    private String pointDescription;
    private String image;

    public static GroupInfoDto of(GroupInfoVo vo){
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
                .image(vo.getImage())
                .build();
    }
}

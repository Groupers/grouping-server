package com.covengers.grouping.dto;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.vo.CreateGroupRequestVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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

    public CreateGroupRequestVo toVo() {
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
                                   .build();
    }
}

package com.covengers.grouping.dto;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.dto.vo.CreateGroupRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CreateGroupRequestDto {
    private String title;
    private Integer maxUserNumber;
    private Integer minUserAge;
    private Integer maxUserAge;
    private Gender availableGender;
    private String description;
    private Long pointX;
    private Long pointY;
    private String pointDescription;

    public CreateGroupRequestVo toVo() {
        return CreateGroupRequestVo.builder()
                .title(title)
                .maxUserNumber(maxUserNumber)
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

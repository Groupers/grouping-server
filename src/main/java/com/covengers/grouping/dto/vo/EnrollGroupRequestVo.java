package com.covengers.grouping.dto.vo;

import com.covengers.grouping.constant.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class EnrollGroupRequestVo {
    private String title;
    private Integer maxUserNumber;
    private Integer minUserAge;
    private Integer maxUserAge;
    private Gender availableGender;
    private String description;
    private Long pointX;
    private Long pointY;
    private String pointDescription;
}
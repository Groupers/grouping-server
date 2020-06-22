package com.covengers.grouping.dto.vo;

import com.covengers.grouping.constant.Gender;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GroupVo {
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

}

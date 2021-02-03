package com.covengers.grouping.vo;

import com.covengers.grouping.constant.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class CreateGroupRequestVo {
    private final String accessToken;
    private final String title;
    private final Boolean isHidden;
    private final Integer minUserAge;
    private final Integer maxUserAge;
    private final Gender availableGender;
    private final String description;
    private final Long pointX;
    private final Long pointY;
    private final String pointDescription;
    private final Long representGroupingUserId;
    private final List<String> hashtagList;
}

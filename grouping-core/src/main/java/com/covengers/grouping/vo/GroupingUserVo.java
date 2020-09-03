package com.covengers.grouping.vo;

import java.time.LocalDate;
import java.util.List;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.constant.NationCode;
import com.covengers.grouping.constant.UserStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GroupingUserVo {

    private final String groupingUserId;
    private final UserStatus userStatus;
    private final String email;
    private final NationCode nationCode;
    private final String phoneNumber;
    private final String name;
    private final String userId;
    private final Gender gender;
    private final LocalDate birthDay;
    private final String representProfileImage;
    private final List<GroupVo> groupVoList;
    private final List<HashtagVo> hashtagVoList;
}

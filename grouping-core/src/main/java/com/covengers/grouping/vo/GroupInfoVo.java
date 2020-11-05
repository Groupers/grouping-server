package com.covengers.grouping.vo;

import com.covengers.grouping.constant.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class GroupInfoVo {
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

    private final String image;

    @Override
    public boolean equals(Object o) {
        if(o instanceof GroupInfoVo) {
            return id.equals(((GroupInfoVo) o).getId());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Long.hashCode(id);
    }
}

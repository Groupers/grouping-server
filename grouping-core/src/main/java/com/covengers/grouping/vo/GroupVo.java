package com.covengers.grouping.vo;

import java.util.Optional;

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

    private final Optional<String> representGroupImage;

    @Override
    public boolean equals(Object o) {
        if (o instanceof GroupVo) {
            return id.equals(((GroupVo) o).getId());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

}

package com.covengers.grouping.dto.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class BringCrewListVo {
    private List<CrewVo> crewVoList;
    private Boolean existCrew;
}

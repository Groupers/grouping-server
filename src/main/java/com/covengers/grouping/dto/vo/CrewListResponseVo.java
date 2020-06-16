package com.covengers.grouping.dto.vo;

import com.covengers.grouping.dto.CrewListResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class CrewListResponseVo {
    private List<CrewVo> crewList;
}

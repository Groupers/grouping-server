package com.covengers.grouping.dto;

import com.covengers.grouping.dto.vo.CrewListResponseVo;
import com.covengers.grouping.dto.vo.CrewVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class CrewListResponseDto {
    private final List<CrewVo> crewList;

    public static CrewListResponseDto of(CrewListResponseVo vo){
        return CrewListResponseDto.builder()
                .crewList(vo.getCrewList())
                .build();
    }
}

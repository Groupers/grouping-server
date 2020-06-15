package com.covengers.grouping.dto;

import com.covengers.grouping.dto.vo.BringCrewListVo;
import com.covengers.grouping.dto.vo.CrewVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class BringCrewListDto {
    private final List<CrewVo> crewVoList;
    private Boolean existCrew;

    public BringCrewListVo toVo(){
        return BringCrewListVo.builder()
                .crewVoList(getCrewVoList())
                .existCrew(getExistCrew())
                .build();
    }

    public static BringCrewListDto of(BringCrewListVo vo){
        return builder()
                .crewVoList(vo.getCrewVoList())
                .existCrew(vo.getExistCrew())
                .build();
    }
}

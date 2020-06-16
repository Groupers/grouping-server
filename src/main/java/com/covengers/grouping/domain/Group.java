package com.covengers.grouping.domain;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.dto.vo.CrewVo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "crew")
public class Crew extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1383129876899942557L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crew_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "max_user_number")
    private Integer maxUserNumber;

    @Column(name = "min_user_age")
    private Integer minUserAge;

    @Column(name = "max_user_age")
    private Integer maxUserAge;

    @Enumerated(EnumType.STRING)
    @Column(name = "available_gender")
    private Gender availableGender;

    @Column(name = "description")
    private String description;

    @Column(name = "point_x")
    private Long pointX;

    @Column(name = "point_y")
    private Long pointY;

    @Column(name = "point_description")
    private String pointDescription;

    @OneToMany(mappedBy = "crew")
    private List<CrewHashtagMapping> crewHashtagMappingList = new ArrayList<>();

    @OneToMany(mappedBy = "crew")
    private List<UserCrewMapping> userCrewMappingList = new ArrayList<>();

    public CrewVo toVoForGroupingUser(){
        return CrewVo.builder()
                .id(getId())
                .title(getTitle())
                .maxUserNumber(getMaxUserNumber())
                .maxUserAge(getMaxUserAge())
                .minUserAge(getMinUserAge())
                .availableGender(getAvailableGender())
                .description(getDescription())
                .pointX(getPointX())
                .pointY(getPointY())
                .pointDescription((getPointDescription()))
                .build();
    }
}

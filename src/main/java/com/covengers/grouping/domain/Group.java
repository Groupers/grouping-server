package com.covengers.grouping.domain;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.dto.vo.GroupVo;
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
public class Group extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1383129876899942557L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
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

    @OneToMany(mappedBy = "group")
    private List<GroupHashtagMapping> groupHashtagMappingList = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<UserGroupMapping> userGroupMappingList = new ArrayList<>();

    public Group(String title,
                 Integer maxUserNumber,
                 Integer maxUserAge,
                 Integer minUserAge,
                 Gender availableGender,
                 String description,
                 Long pointX,
                 Long pointY,
                 String pointDescription) {

        this.title = title;
        this.maxUserNumber = maxUserNumber;
        this.maxUserAge = maxUserAge;
        this.minUserAge = minUserAge;
        this.availableGender = availableGender;
        this.description = description;
        this.pointX = pointX;
        this.pointY = pointY;
        this.pointDescription = pointDescription;
    }

    public GroupVo toVoForGroupingUser(){
        return GroupVo.builder()
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

    public GroupVo toVo(){
        return GroupVo.builder()
                .id(id)
                .title(title)
                .maxUserNumber(maxUserNumber)
                .maxUserAge(maxUserAge)
                .minUserAge(minUserAge)
                .availableGender(availableGender)
                .description(description)
                .pointX(pointX)
                .pointY(pointY)
                .pointDescription(description)
                .build();
    }
}

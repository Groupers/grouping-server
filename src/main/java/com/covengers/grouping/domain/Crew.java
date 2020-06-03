package com.covengers.grouping.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.covengers.grouping.constant.Gender;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}

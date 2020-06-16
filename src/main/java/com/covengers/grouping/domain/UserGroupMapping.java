package com.covengers.grouping.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "user_crew_mapping")
public class UserCrewMapping extends AbstractAuditingEntity {

    private static final long serialVersionUID = -2710275756256697368L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_crew_mapping_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "grouping_user_id")
    private GroupingUser groupingUser;
    @ManyToOne
    @JoinColumn(name = "crew_id")
    private Crew crew;

}

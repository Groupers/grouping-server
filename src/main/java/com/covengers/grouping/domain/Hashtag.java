package com.covengers.grouping.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "hashtag")
public class Hashtag extends AbstractAuditingEntity {

    private static final long serialVersionUID = -7810757704290856812L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    @Column(name = "hashtag")
    private String hashtag;

    @OneToMany(mappedBy = "hashtag")
    private List<CrewHashtagMapping> crewHashtagMappingList = new ArrayList<>();

    @OneToMany(mappedBy = "hashtag")
    private List<UserHashtagMapping> userHashtagMappingList = new ArrayList<>();

}

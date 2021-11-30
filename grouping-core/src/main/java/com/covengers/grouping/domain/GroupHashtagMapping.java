package com.covengers.grouping.domain;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "group_hashtag_mapping")
public class GroupHashtagMapping extends AbstractAuditingEntity {

    private static final long serialVersionUID = -2725017004732800746L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_hashtag_mapping_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;

    public GroupHashtagMapping(Group group, Hashtag hashtag) {
        this.group = group;
        this.hashtag = hashtag;
    }
}

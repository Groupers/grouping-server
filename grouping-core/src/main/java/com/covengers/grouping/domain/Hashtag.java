package com.covengers.grouping.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.covengers.grouping.vo.GroupVo;
import com.covengers.grouping.vo.HashtagVo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
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
    private List<GroupHashtagMapping> groupHashtagMappingList = new ArrayList<>();

    public List<GroupVo> toGroupList() {
        return groupHashtagMappingList.stream()
                                      .map(groupHashtagMapping ->
                                                   groupHashtagMapping.getGroup()
                                                                      .toVoForHashtag())
                                      .collect(Collectors.toList());
    }

    public HashtagVo toVoForGroup() {
        return HashtagVo.builder()
                        .id(id)
                        .hashtag(hashtag)
                        .build();
    }

    public Hashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}

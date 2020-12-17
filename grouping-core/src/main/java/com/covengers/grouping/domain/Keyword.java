package com.covengers.grouping.domain;

import com.covengers.grouping.vo.KeywordVo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "keyword")
public class Keyword extends AbstractAuditingEntity {

    private static final long serialVersionUID = 8828364393278084521L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long id;

    @Column(name = "keyword")
    private String keyword;

    public KeywordVo toVo() {
        return KeywordVo.builder()
                .keyword(getKeyword())
                .build();
    }

    public Keyword(String keyword) {
        this.keyword = keyword;
    }
}

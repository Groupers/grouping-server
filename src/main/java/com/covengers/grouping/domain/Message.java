package com.covengers.grouping.domain;

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
@Table(name = "message")
public class Message extends AbstractAuditingEntity {

    private static final long serialVersionUID = -5389533950883342791L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @Column(name = "message_contents")
    private String message_contents;

    @Column(name = "message_type")
    private String message_type;

    @Column(name = "message_create_time")
    private String message_create_time;

    @OneToMany(mappedBy = "message")
    private List<MessageUserMapping> messageUserMappingList = new ArrayList<>();
}

package com.covengers.grouping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "room_type")
@Table(name = "chat_room")
public abstract class ChatRoom extends AbstractAuditingEntity {

    private static final long serialVersionUID = 3479252314649405287L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    protected Long id;

    @Column(name = "title")
    protected String title;

    @Column(name = "topic_id")
    protected String topicId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    protected List<GroupingUser> userList = new ArrayList<>();

}

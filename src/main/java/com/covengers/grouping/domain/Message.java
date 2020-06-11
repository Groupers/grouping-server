package com.covengers.grouping.domain;

import com.covengers.grouping.constant.MessageType;
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
    private String contents;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type")
    private MessageType type;

    @OneToMany(mappedBy = "message")
    private List<UserMessageMapping> userMessageMappingList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="grouping_user_id")
    private GroupingUser groupingUser;

    @ManyToOne
    @JoinColumn(name="chat_room")
    private ChatRoom chatRoom;
}

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.covengers.grouping.constant.MessageType;
import com.covengers.grouping.vo.MessageVo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name="grouping_user_id")
    private GroupingUser groupingUser;

    @ManyToOne
    @JoinColumn(name="chat_room")
    private ChatRoom chatRoom;

    public MessageVo toVo(){
        return MessageVo.builder()
                .id(id)
                .contents(contents)
                .type(type)
                .groupingUserVo(groupingUser.toVo())
                .chatRoomVo(chatRoom.toVo())
                .build();
    }
}

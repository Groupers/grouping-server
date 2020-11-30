package com.covengers.grouping.domain;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.vo.ChatRoomVo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "chat_room")
public class ChatRoom extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1489983754860461043L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "topic_id")
    private String topicId;


    public ChatRoom(String title) {
        this.title = title;
        this.topicId = UUID.randomUUID().toString();
    }

    public ChatRoomVo toVo() {
        return ChatRoomVo.builder()
                .id(id)
                .topicId(topicId)
                .title(title)
                .build();
    }
}

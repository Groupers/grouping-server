package com.covengers.grouping.domain;

import com.covengers.grouping.dto.vo.ChatRoomVo;
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
@Table(name = "chat_room")
public class ChatRoom extends AbstractAuditingEntity {

    private static final long serialVersionUID = 3866974593025453517L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "chatRoom")
    private List<Message> messageList = new ArrayList<>();

    public ChatRoomVo toVo(){
        return ChatRoomVo.builder()
                .id(id)
                .name(name)
                .build();
    }
}

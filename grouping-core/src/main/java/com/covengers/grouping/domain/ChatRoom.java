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

import com.covengers.grouping.vo.ChatRoomVo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public ChatRoomVo toVo(){
        return ChatRoomVo.builder()
                .id(id)
                .name(name)
                .build();
    }
}

package com.covengers.grouping.domain;

import com.covengers.grouping.vo.GroupChatRoomVo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "group_chat_room")
@DiscriminatorValue("group")
public class GroupChatRoom extends ChatRoom {

    private static final long serialVersionUID = 1489983754860461043L;

    public GroupChatRoom(String title) {
        this.title = title;
        this.topicId = UUID.randomUUID().toString();
    }

    public GroupChatRoomVo toVo() {
        return GroupChatRoomVo.builder()
                .id(id)
                .topicId(topicId)
                .title(title)
                .build();
    }

}

package com.covengers.grouping.domain;

import com.covengers.grouping.vo.GroupChatRoomVo;
import com.covengers.grouping.vo.UserChatRoomVo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "user_chat_room")
@DiscriminatorValue("user")
public class UserChatRoom extends ChatRoom {

    private static final long serialVersionUID = 2038452733314775937L;

    public UserChatRoom() {
        this.topicId = UUID.randomUUID().toString();
    }

    public UserChatRoomVo toVo() {
        return UserChatRoomVo.builder()
                .id(id)
                .topicId(topicId)
                .build();
    }

}

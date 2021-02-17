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
public class GroupChatRoom extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1489983754860461043L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_chat_room_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "topic_id")
    private String topicId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_chat_room_id")
    private List<GroupingUser> userList = new ArrayList<>();

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

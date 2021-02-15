package com.covengers.grouping.service;

import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.domain.ChatMessage;
import com.covengers.grouping.domain.Group;
import com.covengers.grouping.domain.GroupChatRoom;
import com.covengers.grouping.domain.GroupingUser;
import com.covengers.grouping.exception.CommonException;
import com.covengers.grouping.repository.GroupChatRoomRepository;
import com.covengers.grouping.repository.GroupRepository;
import com.covengers.grouping.repository.GroupingUserRepository;
import com.covengers.grouping.vo.CreateGroupChatRoomRequestVo;
import com.covengers.grouping.vo.GroupChatRoomVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final GroupChatRoomRepository groupChatRoomRepository;
    private final GroupRepository groupRepository;
    private final GroupingUserRepository groupingUserRepository;
    private final SimpMessageSendingOperations messagingTemplate;

    @Transactional
    public GroupChatRoomVo createGroupChatRoom(CreateGroupChatRoomRequestVo requestVo) {

        final Optional<Group> groupOptional = groupRepository.findById(requestVo.getGroupId());

        final Group group =
                groupOptional.orElseThrow(() -> new CommonException(ResponseCode.GROUP_NOT_EXISTED));

        final GroupChatRoom groupChatRoom = new GroupChatRoom(requestVo.getChatRoomTitle());

        groupChatRoomRepository.save(groupChatRoom);

        group.getGroupChatRoomList().add(groupChatRoom);

        final List<Long> groupingUserIdList = requestVo.getGroupingUserIdList();

        for (Long id : groupingUserIdList) {
            final Optional<GroupingUser> groupingUserOptional = groupingUserRepository.findById(id);

            final GroupingUser groupingUser =
                    groupingUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

            groupChatRoom.getChatUserList().add(groupingUser);
        }
        return groupChatRoom.toVo();
    }

    /**
     * Not Used Now
     * public ChatRoomVo enterChatRoom(Long chatRoomId) {
     * final ChatRoom chatRoom = chatRoomRepository.getOne(chatRoomId);
     * final String topicId = chatRoom.getTopicId();
     * return chatRoom.toVo();
     * }
     */

    public void sendMessage(ChatMessage chatMessage) {
        messagingTemplate.convertAndSend("/topic/" + chatMessage.getTopicId(), chatMessage);
    }

}

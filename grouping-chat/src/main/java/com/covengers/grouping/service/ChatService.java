package com.covengers.grouping.service;

import com.covengers.grouping.component.GroupRepositoryDecorator;
import com.covengers.grouping.component.GroupingUserRepositoryDecorator;
import com.covengers.grouping.domain.*;
import com.covengers.grouping.dto.CreateUserChatRoomRequestDto;
import com.covengers.grouping.repository.GroupChatRoomRepository;
import com.covengers.grouping.repository.UserChatRoomRepository;
import com.covengers.grouping.vo.CreateGroupChatRoomRequestVo;
import com.covengers.grouping.vo.CreateUserChatRoomRequestVo;
import com.covengers.grouping.vo.GroupChatRoomVo;
import com.covengers.grouping.vo.UserChatRoomVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final GroupChatRoomRepository groupChatRoomRepository;
    private final UserChatRoomRepository userChatRoomRepository;
    private final GroupRepositoryDecorator groupRepository;
    private final GroupingUserRepositoryDecorator groupingUserRepository;
    private final SimpMessageSendingOperations messagingTemplate;

    @Transactional
    public GroupChatRoomVo createGroupChatRoom(CreateGroupChatRoomRequestVo requestVo) {

        final Group group = groupRepository.findById(requestVo.getGroupId());

        final GroupChatRoom groupChatRoom = new GroupChatRoom(requestVo.getChatRoomTitle());

        groupChatRoomRepository.save(groupChatRoom);

        group.getGroupChatRoomList().add(groupChatRoom);

        final List<Long> groupingUserIdList = requestVo.getGroupingUserIdList();

        for (Long id : groupingUserIdList) {
            final GroupingUser groupingUser = groupingUserRepository.findTopById(id);
            groupChatRoom.getUserList().add(groupingUser);
        }

        return groupChatRoom.toVo();
    }

    @Transactional
    public UserChatRoomVo createUserChatRoom(CreateUserChatRoomRequestVo requestVo) {

        final UserChatRoom userChatRoom = new UserChatRoom();

        userChatRoomRepository.save(userChatRoom);

        final List<Long> groupingUserIdList = requestVo.getGroupingUserIdList();

        for (Long id : groupingUserIdList) {
            final GroupingUser groupingUser = groupingUserRepository.findTopById(id);
            userChatRoom.getUserList().add(groupingUser);
        }

        return userChatRoom.toVo();
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

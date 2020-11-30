package com.covengers.grouping.service;

import com.covengers.grouping.domain.ChatRoom;
import com.covengers.grouping.repository.ChatRoomRepository;
import com.covengers.grouping.vo.ChatRoomVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final RedisMessageListenerContainer redisMessageListener;
    private final RedisSubscriber redisSubscriber;
    private Map<String, ChannelTopic> topics;

    @PostConstruct
    private void init() {
        topics = new HashMap<>();
    }

    public ChatRoomVo createChatRoom(String title) {
        final ChatRoom chatRoom = new ChatRoom(title);
        chatRoomRepository.save(chatRoom);
        return chatRoom.toVo();
    }

    public ChatRoomVo enterChatRoom(String chatRoomId) {
        final ChatRoom chatRoom = chatRoomRepository.getOne(chatRoomId);
        final String topicId = chatRoom.getTopicId();
        ChannelTopic topic = topics.get(topicId);
        if (topic==null) {
            topic = new ChannelTopic(topicId);
            redisMessageListener.addMessageListener(redisSubscriber, topic);
            topics.put(topicId, topic);
        }
        return chatRoom.toVo();
    }

    public ChannelTopic getTopic(String topicId) {
        return topics.get(topicId);
    }


}

package com.covengers.grouping.repository;

import com.covengers.grouping.domain.ChatRoom;
import com.covengers.grouping.service.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ChatRoomRepository {

    private final RedisMessageListenerContainer redisMessageListener;
    private final RedisSubscriber redisSubscriber;
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String CHAT_ROOM = "CHAT_ROOM";
    private HashOperations<String, String, ChatRoom> hashOpsChatRoom;
    private Map<String, ChannelTopic> topics;

    @PostConstruct
    private void init() {
        hashOpsChatRoom = redisTemplate.opsForHash();
        topics = new HashMap<>();
    }

    public List<ChatRoom> findAllChatRoom() {
        return hashOpsChatRoom.values(CHAT_ROOM);
    }

    public ChatRoom findChatRoomById(String chatRoomId) {
        return hashOpsChatRoom.get(CHAT_ROOM, chatRoomId);
    }

    public ChatRoom createChatRoom(String title) {
        ChatRoom chatRoom = ChatRoom.create(title);
        hashOpsChatRoom.put(CHAT_ROOM, chatRoom.getId(), chatRoom);
        return chatRoom;
    }

    public void enterChatRoom(String chatRoomId) {
        ChannelTopic topic = topics.get(chatRoomId);
        if (topic==null) {
            topic = new ChannelTopic(chatRoomId);
            redisMessageListener.addMessageListener(redisSubscriber, topic);
            topics.put(chatRoomId, topic);
        }
    }

    public ChannelTopic getTopic(String chatRoomId) {
        return topics.get(chatRoomId);
    }
}

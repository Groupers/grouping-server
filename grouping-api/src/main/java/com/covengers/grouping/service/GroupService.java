package com.covengers.grouping.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.covengers.grouping.component.GroupRepositoryDecorator;
import com.covengers.grouping.component.GroupingUserRepositoryDecorator;
import com.covengers.grouping.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.covengers.grouping.component.HashtagRecommender;
import com.covengers.grouping.constant.GroupUserType;
import com.covengers.grouping.domain.Group;
import com.covengers.grouping.domain.GroupHashtagMapping;
import com.covengers.grouping.domain.GroupingUser;
import com.covengers.grouping.domain.Hashtag;
import com.covengers.grouping.domain.UserGroupMapping;
import com.covengers.grouping.repository.GroupHashtagMappingRepository;
import com.covengers.grouping.repository.HashtagRepository;
import com.covengers.grouping.repository.UserGroupMappingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupService {
    private final HashtagRecommender hashtagRecommender;
    private final GroupRepositoryDecorator groupRepository;
    private final HashtagRepository hashtagRepository;
    private final GroupHashtagMappingRepository groupHashtagMappingRepository;
    private final GroupingUserRepositoryDecorator groupingUserRepository;
    private final UserGroupMappingRepository userGroupMappingRepository;

    @Transactional
    public GroupVo createGroup(CreateGroupRequestVo requestVo) {

        final Group group = new Group(
                requestVo.getTitle(),
                requestVo.getMaxUserAge(),
                requestVo.getMinUserAge(),
                requestVo.getAvailableGender(),
                requestVo.getDescription(),
                requestVo.getPointX(),
                requestVo.getPointY(),
                requestVo.getPointDescription(),
                "url");

        groupRepository.save(group);

        final GroupingUser groupingUser = groupingUserRepository.findTopById(requestVo.getRepresentGroupingUserId());

        final UserGroupMapping userGroupMapping = new UserGroupMapping(groupingUser, group, GroupUserType.MASTER);

        userGroupMappingRepository.save(userGroupMapping);

        for (String hashtagString : requestVo.getHashtagList()) {
            final Optional<Hashtag> hashtagOptional = hashtagRepository.findByHashtag(hashtagString);

            final Hashtag hashtag = hashtagOptional.orElse(new Hashtag(hashtagString));

            hashtagRepository.save(hashtag);

            final GroupHashtagMapping groupHashtagMapping = new GroupHashtagMapping(group, hashtag);

            groupHashtagMappingRepository.save(groupHashtagMapping);
        }

        return group.toVo();
    }

    @Transactional
    public GroupVo uploadGroupImage(MultipartFile imageFile, Long groupId) throws IOException {
        imageFile.transferTo(new File(System.getProperty("user.home")+ "/" + imageFile.getOriginalFilename()));
        final Group group = groupRepository.findById(groupId);
        group.setRepresentGroupImage(imageFile.getOriginalFilename());
        return group.toVo();
    }

    public RecommendGroupVo recommendGroup(String keyword) {

        final RecommendHashtagVo recommendHashtagVo = hashtagRecommender.recommend(keyword);

        final HashSet<GroupVo> groupHashSet = new HashSet<>();

        for (String hashtagString : recommendHashtagVo.getHashtagList()) {
            final Optional<Hashtag> hashtag = hashtagRepository.findByHashtag(hashtagString);

            if (hashtag.isEmpty()) {
                continue;
            }

            final List<GroupVo> list = hashtag.get().toGroupList();
            groupHashSet.addAll(list);
        }

        return RecommendGroupVo
                .builder()
                .groupList(new ArrayList<>(groupHashSet))
                .build();
    }

    public GroupChatRoomListResponseVo getGroupChatRoomList(Long groupId) {

        final Group group = groupRepository.findById(groupId);

        return GroupChatRoomListResponseVo.builder()
                .groupChatRoomList(group.toGroupChatRoomList())
                .build();
    }
}

package com.covengers.grouping.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.covengers.grouping.component.HashtagRecommender;
import com.covengers.grouping.domain.Group;
import com.covengers.grouping.domain.Hashtag;
import com.covengers.grouping.repository.GroupRepository;
import com.covengers.grouping.repository.HashtagRepository;
import com.covengers.grouping.vo.CreateGroupRequestVo;
import com.covengers.grouping.vo.GroupVo;
import com.covengers.grouping.vo.RecommendGroupVo;
import com.covengers.grouping.vo.RecommendHashtagVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupService {
    private final RedisTemplate<String, String> redisTemplate;
    private final HashtagRecommender hashtagRecommender;
    private final GroupRepository groupRepository;
    private final HashtagRepository hashtagRepository;

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
                requestVo.getPointDescription());

        groupRepository.save(group);

        return group.toVo();
    }

    public RecommendGroupVo recommendGroup(String keyword){

        final RecommendHashtagVo recommendHashtagVo = hashtagRecommender.recommend(keyword);

        final HashSet<GroupVo> groupHashSet = new HashSet<>();

        for (String hashtagString : recommendHashtagVo.getHashtagList()) {
            final Optional<Hashtag> hashtag = hashtagRepository.findByHashtag(hashtagString);

            if(!hashtag.isPresent()) {
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
}

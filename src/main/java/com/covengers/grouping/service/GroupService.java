package com.covengers.grouping.service;

import com.covengers.grouping.component.HashtagRecommender;
import com.covengers.grouping.domain.Group;
import com.covengers.grouping.domain.Hashtag;
import com.covengers.grouping.dto.vo.*;
import com.covengers.grouping.repository.GroupRepository;
import com.covengers.grouping.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        HashSet<GroupVo> groupHashSet = new HashSet<>();

        for (String hashtagString : recommendHashtagVo.getHashtagList()) {
            Optional<Hashtag> hashtag = hashtagRepository.findByHashtag(hashtagString);
            if(!hashtag.isPresent())
                continue;

            List<GroupVo> list = hashtag.get().toGroupList();
            for (GroupVo groupVo : list) {
                groupHashSet.add(groupVo);
            }
        }

        return RecommendGroupVo
                .builder()
                .groupList(groupHashSet.stream()
                            .collect(Collectors.toList()))
                .build();
    }
}

package com.covengers.grouping.service;

import com.covengers.grouping.domain.Group;
import com.covengers.grouping.dto.vo.CreateGroupRequestVo;
import com.covengers.grouping.dto.vo.GroupVo;
import com.covengers.grouping.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupService {
    private final RedisTemplate<String, String> redisTemplate;
    private final GroupRepository groupRepository;

    @Transactional
    public GroupVo createGroup(CreateGroupRequestVo requestVo) {

        Group group = new Group(requestVo.getTitle(),
                                requestVo.getMaxUserNumber(),
                                requestVo.getMaxUserAge(),
                                requestVo.getMinUserAge(),
                                requestVo.getAvailableGender(),
                                requestVo.getDescription(),
                                requestVo.getPointX(),
                                requestVo.getPointY(),
                                requestVo.getPointDescription()
                                );

        groupRepository.save(group);

        return group.toVo();
    }
}

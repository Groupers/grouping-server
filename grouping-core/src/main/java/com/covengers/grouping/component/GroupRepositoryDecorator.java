package com.covengers.grouping.component;

import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.domain.Group;
import com.covengers.grouping.exception.CommonException;
import com.covengers.grouping.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Component
public class GroupRepositoryDecorator {

    private final GroupRepository groupRepository;

    public Group findById(Long id) {
        final Optional<Group> groupOptional = groupRepository.findById(id);

        final Group group =
                groupOptional.orElseThrow(() -> new CommonException(ResponseCode.GROUP_NOT_EXISTED));

        return group;
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }


}

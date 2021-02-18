package com.covengers.grouping.component;

import com.covengers.grouping.constant.NationCode;
import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.domain.GroupingUser;
import com.covengers.grouping.exception.CommonException;
import com.covengers.grouping.repository.GroupingUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class GroupingUserRepositoryDecorator {

    private final GroupingUserRepository groupingUserRepository;

    public GroupingUser findTopById(Long id) {
        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopById(id);

        final GroupingUser groupingUser = groupingUserOptional.orElseThrow(
                        () -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        return groupingUser;
    }

    public Optional<GroupingUser> findTopByEmail(String email) {
        return groupingUserRepository.findTopByEmail(email);
    }

    public Optional <GroupingUser> findTopByPhoneNumberAndNationCode(
            String phoneNumber, NationCode nationCode) {
        return groupingUserRepository.findTopByPhoneNumberAndNationCode(phoneNumber, nationCode);
    }

    public GroupingUser save(GroupingUser groupingUser) {
        return groupingUserRepository.save(groupingUser);
    }
}

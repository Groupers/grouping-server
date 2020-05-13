package com.covengers.grouping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covengers.grouping.constant.NationCode;
import com.covengers.grouping.domain.GroupingUser;

public interface GroupingUserRepository extends JpaRepository<GroupingUser, String> {

    Optional<GroupingUser> findTopByEmail(String email);

    Optional<GroupingUser> findTopByUserId(String userId);

    Optional<GroupingUser> findTopByPhoneNumberAndNationCode(String phoneNumber, NationCode nationCode);
}

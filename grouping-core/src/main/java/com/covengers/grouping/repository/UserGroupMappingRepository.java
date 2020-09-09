package com.covengers.grouping.repository;

import com.covengers.grouping.domain.UserGroupMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupMappingRepository extends JpaRepository<UserGroupMapping, Long> {
}

package com.covengers.grouping.repository;

import com.covengers.grouping.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, String> {
    
}

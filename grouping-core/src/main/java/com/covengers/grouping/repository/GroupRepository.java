package com.covengers.grouping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covengers.grouping.domain.Group;

public interface GroupRepository extends JpaRepository<Group, String> {

}

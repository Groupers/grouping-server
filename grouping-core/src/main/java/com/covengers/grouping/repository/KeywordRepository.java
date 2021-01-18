package com.covengers.grouping.repository;

import com.covengers.grouping.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    List<String> findKeywordByCreatedAtAfter(LocalDateTime createdAt);
}

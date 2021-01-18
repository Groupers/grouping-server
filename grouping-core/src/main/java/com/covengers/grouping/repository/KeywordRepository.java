package com.covengers.grouping.repository;

import com.covengers.grouping.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    @Query("select k.keyword from Keyword k where k.createdAt >= :startPoint")
    List<String> findKeywordByCreatedAtAfter(@Param("startPoint") LocalDateTime startPoint);
    
}

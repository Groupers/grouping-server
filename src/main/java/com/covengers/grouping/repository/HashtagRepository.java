package com.covengers.grouping.repository;

import com.covengers.grouping.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, String> {

    Optional<Hashtag> findByHashtag(String hashtag);
}

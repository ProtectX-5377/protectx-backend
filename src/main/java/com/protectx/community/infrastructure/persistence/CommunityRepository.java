package com.protectx.community.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.protectx.community.domain.model.Community;

import java.util.Optional;
import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    Optional<Community> findByName(String name);
    boolean existsByName(String name);


}

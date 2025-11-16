package com.protectx.cameras.infrastructure.persistence;

import com.protectx.cameras.domain.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {
    Optional<Camera> findByIpAddress(String ipAddress);
    boolean existsByIpAddress(String ipAddress);
    List<Camera> findByStatus(String status);
    List<Camera> findByType(String type);

    // Si hay relación con User:
    // List<Camera> findByUserId(Long userId);
}
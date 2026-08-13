package com.backend.pdunghh.hr.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.pdunghh.hr.entity.StaffEntity;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, UUID> {
    Optional<StaffEntity> findByUserId(UUID userId);
}

package com.backend.pdunghh.auth.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.pdunghh.auth.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

     Optional<UserEntity> findByEmailIgnoreCase(String email);

     boolean existsByEmailIgnoreCase(String email);

     Optional<UserEntity> findByPhone(String phone);

     boolean existsByPhone(String phone);
}

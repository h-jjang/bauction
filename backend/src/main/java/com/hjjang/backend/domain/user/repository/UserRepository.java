package com.hjjang.backend.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hjjang.backend.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserById(String id);
}

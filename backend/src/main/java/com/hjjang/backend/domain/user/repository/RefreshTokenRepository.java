package com.hjjang.backend.domain.user.repository;

import com.hjjang.backend.domain.user.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

}

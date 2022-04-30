package com.hjjang.backend.domain.user.repository;

import com.hjjang.backend.domain.user.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    UserRefreshToken findByProviderId(String providerId);

    UserRefreshToken findByProviderIdAndRefreshToken(String providerId, String refreshToken);

}

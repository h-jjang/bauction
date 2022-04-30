package com.hjjang.backend.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor(access = PROTECTED)
@Table(name = "user_refresh_token")
public class UserRefreshToken {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "provider_id", nullable = false, length = 64)
    private String providerId;

    @Column(name = "refresh_token", nullable = false, length = 256)
    @NotNull
    private String refreshToken;

    @Builder
    public UserRefreshToken(String providerId, String refreshToken) {
        this.providerId = providerId;
        this.refreshToken = refreshToken;
    }

    public void reissueRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

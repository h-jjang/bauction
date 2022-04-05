package com.hjjang.backend.domain.user.entity;

import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor(access = PROTECTED)
@Table(name = "refresh_token")
public class RefreshToken {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "refresh_token", nullable = false, length = 256)
    @NotNull
    private String refreshToken;

    @Column(name = "user_id", nullable = false, length = 50)
    private Long userId;

}

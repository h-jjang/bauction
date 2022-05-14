package com.hjjang.backend.domain.user.entity;

import com.hjjang.backend.global.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor(access = PROTECTED)
@Table(name = "user")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "provider_id", nullable = false, length = 128)
    private String providerId;

    @Column(name = "nickname", nullable = false, length = 30)
    private String nickName;

    @Column(name = "email", nullable = true, length = 50)
    private String email;

    @Column(name = "manner_temperature", nullable = false)
    private Long mannerTemperature;

    @Column(name = "image_url", nullable = true, length = 50)
    private String imageUrl;

    @Enumerated(STRING)
    @Column(name = "is_push_agree", nullable = false, length = 10)
    private Agreement isPushAgree;

    @Column(name = "univ_id")
    private Long univId;

    @Column(name = "role", length = 20)
    @Enumerated(STRING)
    private RoleType role;

    @Column(name = "is_email_verification", length = 20)
    private boolean isEmailVerification;

    @Builder
    public User(String providerId, String nickName, String email, Long mannerTemperature,
                String imageUrl, Agreement isPushAgree, Long univId, RoleType role, boolean isEmailVerification) {
        this.providerId = providerId;
        this.nickName = nickName;
        this.email = email;
        this.mannerTemperature = mannerTemperature;
        this.imageUrl = imageUrl;
        this.isPushAgree = isPushAgree;
        this.univId = univId;
        this.role = role;
        this.isEmailVerification = isEmailVerification;
    }
}

package com.hjjang.backend.domain.user.entity;

import static javax.persistence.EnumType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hjjang.backend.domain.university.entity.University;
import com.hjjang.backend.global.domain.BaseTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "image_url", length = 50)
    private String imageUrl;

    @Enumerated(STRING)
    @Column(name = "is_push_agree", nullable = false, length = 10)
    private Agreement isPushAgree;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "univ_id", nullable = true)
    private University university;

    @Column(name = "role", length = 20)
    @Enumerated(STRING)
    private RoleType role;

    @Column(name = "is_email_verification")
    private Boolean isEmailVerification;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @Builder
    public User(String providerId, String nickName, String email, Long mannerTemperature,
                String imageUrl, Agreement isPushAgree, University university, RoleType role, Boolean isEmailVerification,
                Boolean isBlocked) {
        this.providerId = providerId;
        this.nickName = nickName;
        this.email = email;
        this.mannerTemperature = mannerTemperature;
        this.imageUrl = imageUrl;
        this.isPushAgree = isPushAgree;
        this.university = university;
        this.role = role;
        this.isEmailVerification = isEmailVerification;
        this.isBlocked = isBlocked;
    }
}

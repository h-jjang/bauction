package com.hjjang.backend.domain.user.entity;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor(access = PROTECTED)
@Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nickname", nullable = false, length = 30)
    private String nickName;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "manner_temperature", nullable = true)
    private Long mannerTemperature;

    @Column(name = "img_url", nullable = true, length = 50)
    private String imgURL;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(STRING)
    @Column(name = "is_push_agree", nullable = false, length = 10)
    private Agreement isPushAgree;

    @Column(name = "univ_id")
    private Long univId;

}

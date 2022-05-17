package com.hjjang.backend.domain.chat.domain;

import com.hjjang.backend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor(access = PROTECTED)
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    private User senderUser;

    @OneToOne(fetch = LAZY)
    private User receiverUser;

    private Long lastRead;

}

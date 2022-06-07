package com.hjjang.backend.domain.chat.domain.entity;

import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.global.domain.BaseCreatedTimeEntity;
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
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "chat_message")
public class ChatMessage extends BaseCreatedTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "sender_user_id")
    private User senderUser;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    // chatting 할 때 이미지 메세지, 이모티콘, 이미지를 구별할 수 있는 dtype
    // 없어도 되지만 명시적으로 선언
    @Column(updatable = false)
    private String dtype;

    public ChatMessage(User senderUser, ChatRoom chatRoom) {
        this.senderUser = senderUser;
        this.chatRoom = chatRoom;

    }
}

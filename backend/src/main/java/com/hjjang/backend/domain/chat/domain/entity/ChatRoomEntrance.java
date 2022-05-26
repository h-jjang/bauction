package com.hjjang.backend.domain.chat.domain.entity;

import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.global.domain.BaseCreatedTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chat_room_entrance")
public class ChatRoomEntrance extends BaseCreatedTimeEntity {
    // 채팅 방 목록을 조회할 때 사용하는 조회용 테이블
    // 장점
    // 1. 마지막 보낸 메시지 join 없이 찾기 가능
    // 2. 별도의 join 없이 사용자 id로 바로 찾을 수 있음

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_chat_message_id")
    private ChatMessage lastChatMessage;

    @Builder
    public ChatRoomEntrance(ChatRoom chatRoom, User user, ChatMessage lastChatMessage) {
        this.chatRoom = chatRoom;
        this.user = user;
        this.lastChatMessage = lastChatMessage;
    }

}
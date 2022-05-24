package com.hjjang.backend.domain.chat.domain.entity;

import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.global.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chat_room")
public class ChatRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "created_by_user_id")
    private User createdByUser;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatRoomUser> chatRoomUsers = new ArrayList<>();

    // 채팅방 삭제 관련 컬럼
    @Column(name = "is_hidden")
    private Boolean isHidden;

    @Builder
    public ChatRoom(User createdByUser, List<ChatRoomUser> chatRoomUsers) {
        this.createdByUser = createdByUser;
        this.chatRoomUsers = chatRoomUsers;
        this.isHidden = false;
    }

}

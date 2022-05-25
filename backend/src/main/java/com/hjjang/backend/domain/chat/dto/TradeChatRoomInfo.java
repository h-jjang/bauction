package com.hjjang.backend.domain.chat.dto;

import com.hjjang.backend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TradeChatRoomInfo {

    // 채팅방안에 있는 사용자 정보
    private List<User> chatRoomUsers;
}

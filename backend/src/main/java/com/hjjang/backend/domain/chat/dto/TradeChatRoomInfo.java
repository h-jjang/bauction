package com.hjjang.backend.domain.chat.dto;

import com.hjjang.backend.domain.chat.domain.entity.ChatRoom;
import com.hjjang.backend.domain.chat.domain.entity.ChatRoomUser;
import com.hjjang.backend.domain.user.dto.UserProfileInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TradeChatRoomInfo {

    // 채팅방안에 있는 사용자 정보
    private List<UserProfileInfo> userProfileInfos;

    public TradeChatRoomInfo(ChatRoom chatRoom) {
        this.userProfileInfos = chatRoom.getChatRoomUsers()
                .stream()
                .map(ChatRoomUser::getUser)
                .map(UserProfileInfo::new)
                .collect(Collectors.toList());
    }
}

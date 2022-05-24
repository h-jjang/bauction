package com.hjjang.backend.domain.chat.dto;

import com.hjjang.backend.domain.user.dto.UserProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateTradeChatRoomResponse {

    private Long chatRoomId;
    private UserProfileDTO createdByUser;
    private List<UserProfileDTO> joinUsers;
}

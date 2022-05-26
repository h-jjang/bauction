package com.hjjang.backend.domain.chat.dto;

import com.hjjang.backend.domain.chat.domain.entity.ChatRoomEntrance;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TradeChatRoomEntranceListResponse {

    List<TradeChatRoomEntranceInfo> tradeChatRoomEntranceInfos;


    public TradeChatRoomEntranceListResponse(List<ChatRoomEntrance> chatRoomEntranceList) {
        this.tradeChatRoomEntranceInfos = chatRoomEntranceList.stream()
                .map(TradeChatRoomEntranceInfo::new)
                .collect(Collectors.toList());
    }
}

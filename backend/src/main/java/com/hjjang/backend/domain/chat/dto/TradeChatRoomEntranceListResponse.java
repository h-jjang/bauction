package com.hjjang.backend.domain.chat.dto;

import com.hjjang.backend.domain.chat.domain.entity.EntranceChatRoom;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TradeChatRoomEntranceListResponse {

    List<TradeChatRoomEntranceInfo> tradeChatRoomEntranceInfos;


    public TradeChatRoomEntranceListResponse(List<EntranceChatRoom> entranceChatRoomList) {
        this.tradeChatRoomEntranceInfos = entranceChatRoomList.stream()
                .map(TradeChatRoomEntranceInfo::new)
                .collect(Collectors.toList());
    }
}

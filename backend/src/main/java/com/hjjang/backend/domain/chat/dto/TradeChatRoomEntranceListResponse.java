package com.hjjang.backend.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TradeChatRoomEntranceListResponse {

    List<TradeChatRoomEntranceInfo> tradeChatRoomEntranceInfos;
}

package com.hjjang.backend.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TradeChatRoomEntranceListResponse {

    Page<TradeChatRoomEntranceInfo> tradeChatRoomEntranceInfos;
}

package com.hjjang.backend.domain.trade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TradeResponseDto {
    Long id;

    @JsonProperty("post_id")
    private Long postId;

    @JsonProperty("buyer_id")
    private Long buyerId;

    @JsonProperty("seller_id")
    private Long sellerId;

    @JsonProperty("state")
    private String state;

}

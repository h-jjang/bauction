package com.hjjang.backend.domain.trade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TradeRequestDto {

    @NotNull
    @JsonProperty("post_id")
    private Long postId;

    @NotNull
    @JsonProperty("buyer_id")
    private Long buyerId;

    @NotNull
    @JsonProperty("seller_id")
    private Long sellerId;
}

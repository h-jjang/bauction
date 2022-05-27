package com.hjjang.backend.domain.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PostResponseDto {

    private Long id;

    private Long university_id;

    private String title;

    private String content;

    private Integer item_price;

    private Integer views;

    private Integer interest_number;

    private Integer chat_number;

    private String is_sale_completion;

    private boolean removed;

    private String time;
}

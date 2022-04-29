package com.hjjang.backend.domain.post.dto;

import com.hjjang.backend.domain.post.domain.entity.Post;
import lombok.Builder;

@Builder
public class PostResponse {

    private Long id;

    private Long user_id;

    private Long image_id;

    private String title;

    private String content;

    private Integer item_price;

    private Integer views;

    private Integer interest_number;

    private Integer chat_number;

    private String is_sale_completion;

    private boolean removed;

    public static PostResponse of(Post post) {
        return PostResponse.builder()
                .id(post.getId())
//                .user_id(post.getUser().getId())
                .image_id(post.getImage().getId())
                .title(post.getTitle())
                .content(post.getContent())
                .item_price(post.getItem_price())
                .views(post.getViews())
                .interest_number(post.getInterest_number())
                .chat_number(post.getChat_number())
                .is_sale_completion(post.getIs_sale_completion())
                .removed(post.isRemoved())
                .build();
    }
}

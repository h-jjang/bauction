package com.hjjang.backend.domain.post.dto;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.user.entity.User;
import lombok.Builder;

@Builder
public class PostResponse {

    private Long id;

    private User seller;

    private String name;

    private int price;


    public static PostResponse of(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .seller(post.getSeller())
                .name(post.getName())
                .price(post.getPrice())
                .build();
    }
}

package com.hjjang.backend.domain.post.dto;

import com.hjjang.backend.domain.post.domain.entity.Post;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class PostRequest {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private int price;

    public Post toEntity() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .item_price(this.price)
                .build();
    }
}

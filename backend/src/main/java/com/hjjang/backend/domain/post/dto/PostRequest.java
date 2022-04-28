package com.hjjang.backend.domain.post.dto;

import com.hjjang.backend.domain.post.domain.entity.Post;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class PostRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private int price;

    public Post toEntity() {
        return Post.builder()
                .name(this.name)
                .price(this.price)
                //TODO 판매자 추가해야함
                .build();
    }
}

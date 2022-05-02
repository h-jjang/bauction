package com.hjjang.backend.domain.post.dto;

import com.hjjang.backend.domain.post.domain.entity.Post;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PostRequest {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String price;

    public Post toEntity() {
        return Post.builder()
//                .user(null)
//                .image(null)
                .title(this.title)
                .content(this.content)
                .item_price(Integer.valueOf(this.price))
                .build();
    }
}

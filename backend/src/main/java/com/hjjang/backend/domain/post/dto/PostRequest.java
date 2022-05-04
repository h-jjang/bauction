package com.hjjang.backend.domain.post.dto;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.user.entity.User;
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
    private int price;

    public Post toEntity(User user) {
        return Post.builder()
                .user(user)
//                .image(null)
                .title(this.title)
                .content(this.content)
                .itemPrice(this.price)
                .build();
    }
}

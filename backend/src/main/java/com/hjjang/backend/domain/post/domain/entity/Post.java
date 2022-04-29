package com.hjjang.backend.domain.post.domain.entity;

import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.infra.image.domain.entity.Image;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = "post")
@Entity
public class Post {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    private String title;

    private String content;

    private Integer item_price;

    private Integer views = 0;

    private Integer interest_number = 0;

    private Integer chat_number = 0;

    private String is_sale_completion;

    private boolean removed = false;

    public void removePost() {
        this.removed = true;
    }
}

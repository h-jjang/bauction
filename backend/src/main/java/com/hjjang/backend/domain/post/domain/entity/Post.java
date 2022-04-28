package com.hjjang.backend.domain.post.domain.entity;

import com.hjjang.backend.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = "item")
@Entity
public class Post {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

//    private List<Image> image_list;

    private Integer price;

    private boolean removed = false;

    public void removeItem() {
        this.removed = true;
    }
}

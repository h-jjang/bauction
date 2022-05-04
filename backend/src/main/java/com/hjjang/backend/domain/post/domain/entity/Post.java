package com.hjjang.backend.domain.post.domain.entity;

import com.hjjang.backend.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Table(name = "post")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "image_id")
//    private Image image;

    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "item_price", nullable = false)
    private int itemPrice;

    @ColumnDefault("0")
    @Column(name = "views", nullable = false)
    private int views = 0;

    @ColumnDefault("0")
    @Column(name = "interest_number", nullable = false)
    private int interestNumber = 0;

    @ColumnDefault("0")
    @Column(name = "chat_number", nullable = false)
    private int chatNumber = 0;

    @ColumnDefault("'false'")
    @Column(name = "is_sale_completion", nullable = false)
    private String isSaleCompletion = "false";

    @ColumnDefault("false")
    @Column(name = "removed", nullable = false)
    private boolean removed = false;

    public void removePost() {
        this.removed = true;
    }
}

package com.hjjang.backend.domain.post.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Table(schema = "post")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

//    @ManyToOne
//    @JoinColumn(name = "image_id")
//    private Image image;

    private String title;

    private String content;

    private Integer item_price;

    @ColumnDefault("0")
    private Integer views = 0;

    @ColumnDefault("0")
    private Integer interest_number = 0;

    @ColumnDefault("0")
    private Integer chat_number = 0;

    @ColumnDefault("'false'")
    private String is_sale_completion = "false";

    @ColumnDefault("false")
    private boolean removed = false;

    public void removePost() {
        this.removed = true;
    }
}

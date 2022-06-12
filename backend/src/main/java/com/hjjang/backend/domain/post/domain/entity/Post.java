package com.hjjang.backend.domain.post.domain.entity;

import static com.hjjang.backend.domain.post.domain.entity.PostDefaultValue.*;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import com.hjjang.backend.domain.post.dto.PostRequestDto;
import com.hjjang.backend.domain.university.entity.University;
import com.hjjang.backend.domain.user.entity.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@DynamicInsert
@Table(name = "post")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university = DEFAULT_UNIVERSITY;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "item_price", nullable = false)
    private int itemPrice;

//    @ColumnDefault("0")
    @Column(name = "views", nullable = false)
    private int views = DEFAULT_VIEWS;

//    @ColumnDefault("0")
    @Column(name = "interest_number", nullable = false)
    private int interestNumber = DEFAULT_INTEREST_NUMBER;

//    @ColumnDefault("0")
    @Column(name = "chat_number", nullable = false)
    private int chatNumber = DEFAULT_CHAT_NUMBER;

    @Enumerated(EnumType.STRING) @Setter
    @Column(name = "is_sale_completion", nullable = false)
    private PostState isSaleCompletion = DEFAULT_IS_SALE_COMPLETION;

//    @ColumnDefault("false")
    @Column(name = "removed", nullable = false)
    private boolean removed = DEFAULT_REMOVED;

    @CreationTimestamp
    private LocalDateTime time;

    @Builder
    public Post(User user, String title, String content, int itemPrice) {
        this.user = user;
        this.university = user.getUniversity();
        this.title = title;
        this.content = content;
        this.itemPrice = itemPrice;
    }

    public void removePost() {
        this.removed = true;
    }

    public Post update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.itemPrice = postRequestDto.getPrice();
        return this;
    }

    public String mailText(String content) {
        return content + "\n"
            + "거래 제목: " + title + "\n"
            + "상태: " + isSaleCompletion.getState();
    }

    @Override
    public String toString() {
        return "Post{" +
            "id=" + id +
            ", user=" + user +
            ", university=" + university +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", itemPrice=" + itemPrice +
            ", views=" + views +
            ", interestNumber=" + interestNumber +
            ", chatNumber=" + chatNumber +
            ", isSaleCompletion=" + isSaleCompletion +
            ", removed=" + removed +
            ", time=" + time +
            '}';
    }
}

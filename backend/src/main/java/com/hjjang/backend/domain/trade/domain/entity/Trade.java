package com.hjjang.backend.domain.trade.domain.entity;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.post.domain.entity.PostState;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.global.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Trade extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private User seller;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TradeState tradeState;

    @Column(nullable = false)
    private boolean removed = false;

    @Builder
    public Trade(Post post, User buyer, User seller) {
        this.post = post;
        this.buyer = buyer;
        this.seller = seller;
        this.tradeState = TradeState.PENDING;
    }

    public Trade update(Trade entity) {
        this.post = entity.getPost();
        this.buyer = entity.getBuyer();
        this.seller = entity.getSeller();
        return this;
    }

    public Trade remove() {
        this.removed = true;
        return this;
    }

    public Trade setTradeState(TradeState tradeState) {
        this.tradeState = tradeState;
        return this;
    }

    public Post setPostState() {
        if (tradeState.equals(TradeState.PENDING)) {
            post.setIsSaleCompletion(PostState.SALE);
        }
        if (tradeState.equals(TradeState.APPROVE)) {
            post.setIsSaleCompletion(PostState.SOLD);
        }
        if (tradeState.equals(TradeState.RESERVE)) {
            post.setIsSaleCompletion(PostState.RESERVED);
        }
        if (tradeState.equals(TradeState.CANCEL)) {
            post.setIsSaleCompletion(PostState.SALE);
        }
        return post;
    }
}

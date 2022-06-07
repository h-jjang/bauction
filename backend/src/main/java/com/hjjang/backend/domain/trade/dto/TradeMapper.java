package com.hjjang.backend.domain.trade.dto;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.post.exception.PostNotFoundException;
import com.hjjang.backend.domain.post.service.PostServiceImpl;
import com.hjjang.backend.domain.trade.domain.entity.Trade;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.domain.user.service.UserProfileService;
import com.hjjang.backend.global.execption.BusinessException;
import com.hjjang.backend.global.response.code.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.LongFunction;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class TradeMapper {

    private final PostServiceImpl postService;
    private final UserProfileService userProfileService;

    public Trade toEntity(TradeRequestDto requestDto) {

        LongFunction<User> findUser = userId -> Stream.of(userId)
                .map(userProfileService::findById)
                .findAny()
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        LongFunction<Post> findPost = postId -> Stream.of(postId)
                .map(postService::findOneById)
                .findAny()
                .orElseThrow(PostNotFoundException::new);

        return Trade.builder()
                .post(findPost.apply(requestDto.getPostId()))
                .buyer(findUser.apply(requestDto.getBuyerId()))
                .seller(findUser.apply(requestDto.getSellerId()))
                .build();
    }

    public TradeResponseDto fromEntity(Trade entity) {
        return TradeResponseDto.builder()
                .id(entity.getId())
                .postId(entity.getPost().getId())
                .buyerId(entity.getBuyer().getId())
                .sellerId(entity.getSeller().getId())
                .state(entity.getTradeState().getState())
                .build();
    }
}
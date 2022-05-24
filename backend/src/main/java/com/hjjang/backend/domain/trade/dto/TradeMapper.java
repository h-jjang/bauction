package com.hjjang.backend.domain.trade.dto;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.post.exception.PostNotFoundException;
import com.hjjang.backend.domain.post.service.PostServiceImpl;
import com.hjjang.backend.domain.trade.domain.entity.Trade;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.domain.user.repository.UserRepository;
import com.hjjang.backend.global.execption.BusinessException;
import com.hjjang.backend.global.response.code.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.LongFunction;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TradeMapper {

    private final PostServiceImpl postService;
    private final UserRepository userRepository;

    LongFunction<User> findUser = userId -> Stream.of(userId)
            .map(userRepository::findById) //유저 리포지토리를 여기서 사용해도 될지 모르겠습니다.
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findAny()
            .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND)); //TODO 유저 관련 예외 생성 필요

    LongFunction<Post> findPost = postId -> Stream.of(postId)
            .map(postService::findOneById)
            .findAny()
            .orElseThrow(PostNotFoundException::new);

    public Trade toEntity(TradeRequestDto requestDto) {
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
                .build();
    }
}
package com.hjjang.backend.domain.post.dto;

import org.springframework.stereotype.Service;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.university.entity.University;

@Service
public class PostMapper {

    public Post toEntity(PostRequestDto postRequestDto, University university) {
        return Post.builder()
                .university(university)
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .itemPrice(postRequestDto.getPrice())
                .build();
    }

    public PostResponseDto fromEntity(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .university_id(post.getUniversity().getId())
                .title(post.getTitle())
                .content(post.getContent())
                .item_price(post.getItemPrice())
                .views(post.getViews())
                .interest_number(post.getInterestNumber())
                .chat_number(post.getChatNumber())
                .is_sale_completion(post.getIsSaleCompletion().getState())
                .removed(post.isRemoved())
                .time(post.getTime().toString())
                .build();
    }

}

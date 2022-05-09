package com.hjjang.backend.domain.post.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDefaultValue {
    public static final int DEFAULT_VIEWS = 0;
    public static final int DEFAULT_INTEREST_NUMBER = 0;
    public static final int DEFAULT_CHAT_NUMBER = 0;
    public static final PostState DEFAULT_IS_SALE_COMPLETION = PostState.SALE;
    public static final boolean DEFAULT_REMOVED = false;
}

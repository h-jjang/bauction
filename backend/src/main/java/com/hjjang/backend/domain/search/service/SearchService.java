package com.hjjang.backend.domain.search.service;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {

	Page<Post> findAll(String filter, Pageable pageable, User user);

	Page<Post> findByKeyword(String keyword, String filter, Pageable pageable, User user);
}

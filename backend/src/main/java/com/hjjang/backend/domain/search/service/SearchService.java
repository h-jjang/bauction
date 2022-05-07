package com.hjjang.backend.domain.search.service;

import com.hjjang.backend.domain.post.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {

	Page<Post> findAll(Pageable pageable);

	Page<Post> findByKeyword(String keyword, Pageable pageable);
}

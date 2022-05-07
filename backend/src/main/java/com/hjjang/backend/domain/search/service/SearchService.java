package com.hjjang.backend.domain.search.service;

import com.hjjang.backend.domain.post.domain.entity.Post;
import java.util.List;

public interface SearchService {

	List<Post> findAll();

	List<Post> findByKeyword(String keyword);
}

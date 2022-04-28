package com.hjjang.backend.domain.search.service;

import com.hjjang.backend.domain.post.entity.Post;
import com.hjjang.backend.domain.search.dto.PostResponse;
import com.hjjang.backend.domain.search.repository.SearchRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

	private final SearchRepository searchRepository;

	public PostResponse findAll() {
		List<Post> posts = searchRepository.findAll();
		return new PostResponse(posts);
	}
}

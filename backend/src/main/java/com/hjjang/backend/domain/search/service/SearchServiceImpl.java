package com.hjjang.backend.domain.search.service;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.search.dto.PostResponse;
import com.hjjang.backend.domain.search.repository.SearchRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final SearchRepository searchRepository;

	public PostResponse findAll() {
		List<Post> posts = searchRepository.findAll();
		return new PostResponse(posts);
	}

	public PostResponse findByKeyword(String keyword) {
		List<Post> posts = new ArrayList<>();
		List<String> keywords = parseKeyword(keyword);
		keywords.forEach(word -> {
			List<Post> searchedPosts = searchRepository.findByTitleContaining(word);
			posts.addAll(posts.size(), searchedPosts);
		});
		return new PostResponse(posts);
	}

	private List<String> parseKeyword(String keyword) {
		return List.of(keyword.split(" "));
	}
}

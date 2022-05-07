package com.hjjang.backend.domain.search.service;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.search.repository.SearchRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final SearchRepository searchRepository;

	public List<Post> findAll() {
		return searchRepository.findAll();
	}

	public List<Post> findByKeyword(String keyword) {
		List<Post> posts = new ArrayList<>();
		List<String> keywords = parseKeyword(keyword);
		keywords.forEach(word -> {
			List<Post> searchedPosts = searchRepository.findByTitleContaining(word);
			posts.addAll(searchedPosts);
		});
		return posts.stream().distinct().collect(Collectors.toList());
	}

	private List<String> parseKeyword(String keyword) {
		return List.of(keyword.split(" "));
	}
}

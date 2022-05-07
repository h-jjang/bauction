package com.hjjang.backend.domain.search.service;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.search.repository.SearchRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final SearchRepository searchRepository;

	public Page<Post> findAll(String filter, Pageable pageable) {
		if (filter == null) {
			return searchRepository.findAll(pageable);
		}
		return searchRepository.findByIsSaleCompletion(filter, pageable);
	}

	public Page<Post> findByKeyword(String keyword, String filter, Pageable pageable) {
		List<Post> posts = new ArrayList<>();
		List<String> keywords = parseKeyword(keyword);
		keywords.forEach(word -> {
			List<Post> searchedPosts = searchRepository.findByTitleContaining(word, pageable);
			posts.addAll(searchedPosts);
		});
		List<Post> distinctPosts = posts.stream().distinct().collect(Collectors.toList());
		return getPostPage(pageable, distinctPosts);
	}

	private PageImpl<Post> getPostPage(Pageable pageable, List<Post> posts) {
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), posts.size());
		return new PageImpl<>(posts.subList(start, end), pageable, posts.size());
	}

	private List<String> parseKeyword(String keyword) {
		return List.of(keyword.split(" "));
	}
}

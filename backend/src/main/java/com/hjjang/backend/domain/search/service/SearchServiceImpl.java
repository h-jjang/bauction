package com.hjjang.backend.domain.search.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.search.repository.SearchRepository;
import com.hjjang.backend.domain.university.entity.University;
import com.hjjang.backend.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final SearchRepository searchRepository;

	public Page<Post> findAll(String filter, Pageable pageable, User user) {
		// TODO::이메일 인증된 유저로 대체
		if (true) {
			return searchByFilterInUniversity(filter, pageable, user.getUniversity());
		}
		return searchByFilter(filter, pageable);
	}

	public Page<Post> findByKeyword(String keyword, String filter, Pageable pageable, User user) {
		// TODO::이메일 인증된 유저로 대체
		if (true) {
			return searchByFilterAndKeywordInUniversity(keyword, filter, user.getUniversity(), pageable);
		}
		return searchByFilterAndKeyword(keyword, filter, pageable);
	}

	private Page<Post> searchByFilterAndKeyword(String keyword, String filter, Pageable pageable) {
		List<Post> posts = new ArrayList<>();
		List<String> keywords = parseKeyword(keyword);
		keywords.forEach(word -> addSearchedPosts(pageable, posts, word));
		if (filter == null) {
			return getPostPage(pageable, sortAndDistinctPosts(posts));
		}
		posts.retainAll(searchRepository.findByIsSaleCompletion(filter, pageable));
		return getPostPage(pageable, sortAndDistinctPosts(posts));
	}

	private void addSearchedPosts(Pageable pageable, List<Post> posts, String word) {
		posts.addAll(searchRepository.findByTitleContaining(word, pageable));
	}

	private Page<Post> searchByFilterAndKeywordInUniversity(String keyword, String filter, University university, Pageable pageable) {
		List<String> keywords = parseKeyword(keyword);
		List<Post> posts = new ArrayList<>();
		keywords.forEach(word ->
			getPostsInUniversity(
				university,
				searchRepository.findByTitleContaining(word, pageable),
				posts)
		);
		if (filter == null) {
			return getPostPage(pageable, sortAndDistinctPosts(posts));
		}
		posts.retainAll(searchRepository.findByIsSaleCompletionAndUniversity(filter, university, pageable));
		return getPostPage(pageable, sortAndDistinctPosts(posts));
	}

	private void getPostsInUniversity(University university, List<Post> searchedPosts, List<Post> searchedPostsInUniv) {
		searchedPosts.forEach(post -> {
			if (Objects.equals(post.getUniversity().getId(), university.getId())) {
				searchedPostsInUniv.add(post);
			}
		});
	}

	private Page<Post> searchByFilterInUniversity(String filter, Pageable pageable, University university) {
		if (filter == null) {
			return searchRepository.findByUniversity(university, pageable);
		}
		return searchRepository.findAllByIsSaleCompletionAndUniversity(filter, university, pageable);
	}

	private Page<Post> searchByFilter(String filter, Pageable pageable) {
		if (filter == null) {
			searchRepository.findAll(pageable);
		}
		return searchRepository.findAllByIsSaleCompletion(filter, pageable);
	}

	private PageImpl<Post> getPostPage(Pageable pageable, List<Post> posts) {
		final int start = (int) pageable.getOffset();
		final int end = Math.min((start + pageable.getPageSize()), posts.size());
		return new PageImpl<>(posts.subList(start, end), pageable, posts.size());
	}

	private List<Post> sortAndDistinctPosts(List<Post> posts) {
		List<Post> distinctPosts = posts.stream().distinct().collect(Collectors.toList());
		return distinctPosts
			.stream()
			.sorted(Comparator.comparing(Post::getTime).reversed())
			.collect(Collectors.toList());
	}

	private List<String> parseKeyword(String keyword) {
		return List.of(keyword.split(" "));
	}
}

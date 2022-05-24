package com.hjjang.backend.domain.search.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.university.entity.University;

public interface SearchRepository extends JpaRepository<Post, Long> {

	List<Post> findByIsSaleCompletion(String IsSaleCompletion, Pageable pageable);

	Page<Post> findAllByIsSaleCompletion(String IsSaleCompletion, Pageable pageable);

	List<Post> findByTitleContaining(String keyword, Pageable pageable);

	Page<Post> findByUniversity(University university, Pageable pageable);

	List<Post> findByIsSaleCompletionAndUniversity(String IsSaleCompletion, University university, Pageable pageable);
	Page<Post> findAllByIsSaleCompletionAndUniversity(String IsSaleCompletion, University university, Pageable pageable);
}

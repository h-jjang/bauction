package com.hjjang.backend.domain.search.repository;

import com.hjjang.backend.domain.post.domain.entity.Post;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Post, Long> {

	List<Post> findByIsSaleCompletion(String IsSaleCompletion, Pageable pageable);

	Page<Post> findAllByIsSaleCompletion(String IsSaleCompletion, Pageable pageable);

	List<Post> findByTitleContaining(String keyword, Pageable pageable);

	Page<Post> findByUnivId(Long univId, Pageable pageable);

	List<Post> findByIsSaleCompletionAndUnivId(String IsSaleCompletion, Long univId, Pageable pageable);

	Page<Post> findAllByIsSaleCompletionAndUnivId(String IsSaleCompletion, Long univId, Pageable pageable);
}

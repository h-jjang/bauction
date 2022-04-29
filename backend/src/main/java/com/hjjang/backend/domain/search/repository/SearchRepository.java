package com.hjjang.backend.domain.search.repository;

import com.hjjang.backend.domain.post.domain.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Post, Long> {

	List<Post> findByTitleContaining(String keyword);
}

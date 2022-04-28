package com.hjjang.backend.domain.search.repository;

import com.hjjang.backend.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Post, Long> {

}

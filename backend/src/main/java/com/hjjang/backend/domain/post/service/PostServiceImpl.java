package com.hjjang.backend.domain.post.service;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.post.domain.repository.PostRepository;
import com.hjjang.backend.domain.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public Post save(Post post){
        return postRepository.save(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findOneById(Long id) {
        return postRepository.findById(id).orElseThrow(PostNotFoundException::new);
    }

    public void deleteOneById(Long id) {
        findOneById(id).removeItem();
    }
}


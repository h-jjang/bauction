package com.hjjang.backend.domain.post.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.post.domain.repository.PostRepository;
import com.hjjang.backend.domain.post.dto.PostRequestDto;
import com.hjjang.backend.domain.post.exception.PostNotFoundException;
import com.hjjang.backend.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findOneById(Long id) {
        return postRepository.findById(id).orElseThrow(PostNotFoundException::new);
    }

    public Post updateOneById(Long id, PostRequestDto postRequestDto, User user) {
        Post foundPost = findOneById(id);
        return save(foundPost.update(postRequestDto));
        // if (user == foundPost.getUser()) return
        // throw new UserNotMatchException("사용자 정보가 일치하지 않습니다.", ErrorCode.NO_AUTHORITY);
    }

    public void deleteOneById(Long id) {
        findOneById(id).removePost();
    }
}


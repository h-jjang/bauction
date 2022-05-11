package com.hjjang.backend.domain.post.service;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.post.domain.repository.PostRepository;
import com.hjjang.backend.domain.post.dto.PostRequestDto;
import com.hjjang.backend.domain.post.exception.PostNotFoundException;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.domain.user.exception.UserNotMatchException;
import com.hjjang.backend.global.response.code.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (user == foundPost.getUser()) return save(foundPost.update(postRequestDto));
        else throw new UserNotMatchException("사용자 정보가 일치하지 않습니다.", ErrorCode.NO_AUTHORITY);
    }

    public void deleteOneById(Long id) {
        findOneById(id).removePost();
    }
}


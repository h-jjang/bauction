package com.hjjang.backend.domain.search.dto;

import com.hjjang.backend.domain.post.entity.Post;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponse {

	private List<Post> posts;
}

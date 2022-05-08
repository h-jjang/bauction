package com.hjjang.backend.category;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hjjang.backend.domain.category.domain.entity.Category;
import com.hjjang.backend.domain.category.dto.CategoryRequest;

class CategoryServiceTest {

	@DisplayName("카테고리 등록 기능")
	@Test
	void 카테고리_등록하기(){
		//given
		String name = "가전제품";
		Category category = new Category(name);
		//when
		CategoryRequest categoryRequest = new CategoryRequest(name);
		//then
		// id 필드는 GeneratedValue에 의해 생성되므로 객체 생성시 null
		assertThat(category.getName()).isEqualTo(categoryRequest.getName());
		assertThat(category.getRemoved()).isEqualTo(false);
	}

}

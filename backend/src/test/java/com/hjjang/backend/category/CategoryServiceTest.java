package com.hjjang.backend.category;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hjjang.backend.domain.category.domain.entity.Category;
import com.hjjang.backend.domain.category.domain.repository.CategoryRepository;
import com.hjjang.backend.domain.category.dto.CategoryRequest;
import com.hjjang.backend.domain.category.service.CategoryService;

@ExtendWith(SpringExtension.class)
class CategoryServiceTest {
	@Mock
	private CategoryService categoryService;
	@Mock
	private CategoryRepository categoryRepository;

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
	@DisplayName("카테고리 전체 조회 기능")
	@Test
	void 카테고리_전체_조회(){
		//given
		String name = "가전제품";
		CategoryRequest categoryRequest = new CategoryRequest(name);
		Category category = categoryRequest.toEntity();
		categoryRepository.save(category);

		//when
		List<Category> categoryList = categoryService.findAll();
		//then
		List<Category> categoryListRepository = categoryRepository.findAll();
		assertThat(categoryList).isEqualTo(categoryListRepository);
	}
}

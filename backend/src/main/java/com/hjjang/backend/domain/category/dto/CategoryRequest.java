package com.hjjang.backend.domain.category.dto;

import javax.validation.constraints.NotEmpty;

import com.hjjang.backend.domain.category.domain.entity.Category;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CategoryRequest {

	@NotEmpty
	private String name;

	public Category toEntity() {
		return Category.builder()
			.name(name)
			.build();
	}
}

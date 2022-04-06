package com.hjjang.backend.domain.category.dto;

import com.hjjang.backend.domain.category.domain.entity.Category;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
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

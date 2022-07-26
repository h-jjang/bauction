package com.hjjang.backend.domain.post.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private int price;
}

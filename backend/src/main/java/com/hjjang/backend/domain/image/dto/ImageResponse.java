package com.hjjang.backend.domain.image.dto;

import com.hjjang.backend.domain.image.domain.entity.Image;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ImageResponse {

    private Long id;
    private String path;

    public static ImageResponse of(Image image) {
        return ImageResponse.builder()
                .id(image.getId())
                .path(image.getPath())
                .build();
    }
}

package com.hjjang.backend.domain.image.dto;

import com.hjjang.backend.domain.image.domain.entity.Image;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ImagePathResponse {

    private Long id;
    private String path;

    public static ImagePathResponse of(Image image) {
        return ImagePathResponse.builder()
                .id(image.getId())
                .path(image.getPath())
                .build();
    }
}

package com.hjjang.backend.domain.image.dto;


import com.hjjang.backend.domain.image.domain.entity.Image;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageDto {
    @NotEmpty
    private String path;

    public Image toEntity() throws IOException {
        return Image.builder()
                .path(this.path)
                .build();
    }
}

package com.hjjang.backend.domain.image.dto;


import com.hjjang.backend.domain.image.component.ImageUploader;
import com.hjjang.backend.domain.image.component.LocalUploader;
import com.hjjang.backend.domain.image.domain.entity.Image;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

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

package com.hjjang.backend.domain.image.service;

import com.hjjang.backend.domain.image.domain.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    List<Image> findAll();

    Image findImageById(Long imageId);

    void uploadNewImage(MultipartFile multipartFile) throws IOException;

    void removeImage(Image image);

    boolean isImageInPath(Image image);
}

package com.hjjang.backend.domain.image.service;

import com.hjjang.backend.domain.image.domain.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    public List<Image> findAll();

    public Image findImageById(Long imageId);

    public void uploadNewImage(MultipartFile multipartFile) throws IOException;

    public void removeImage(Image image);
}

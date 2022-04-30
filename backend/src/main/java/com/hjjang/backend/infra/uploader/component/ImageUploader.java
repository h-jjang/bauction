package com.hjjang.backend.infra.uploader.component;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface ImageUploader {

    String upload(MultipartFile multipartFile) throws IOException;

    String changeFileName(MultipartFile uploadFile);

    Optional<File> localUpload(MultipartFile file) throws IOException;
}

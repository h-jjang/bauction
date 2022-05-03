package com.hjjang.backend.domain.image.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class LocalImageUploader extends ImageUploader{

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        File uploadFile =
                this.localUpload(multipartFile) // 파일 변환할 수 없으면 에러
                        .orElseThrow(
                                () -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));
        return uploadFile.getName();
    }

}
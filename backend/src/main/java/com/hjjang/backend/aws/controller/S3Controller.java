package com.hjjang.backend.aws.controller;

import com.hjjang.backend.aws.component.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class S3Controller {

    private final S3Uploader s3Uploader;

    //TODO: 이미지가 일정 용량 이상일 경우 업로드가 안되는데, 그 과정에서 이미지가 로컬에 저장은 되고 방치됌 -> 동일 파일 이름으로 이미지 업로드시 오류 유발
    @PostMapping("/images")
    public String upload(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        s3Uploader.upload(multipartFile, "static");
        return "test";
    }
}

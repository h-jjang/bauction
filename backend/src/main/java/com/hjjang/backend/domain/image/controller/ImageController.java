package com.hjjang.backend.domain.image.controller;

import com.hjjang.backend.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageUploadController {

    private final ImageService imageService;

    //TODO: 이미지가 일정 용량이상일 경우 업로드가 안되는데, 그 과정에서 이미지가 로컬에 저장은 되고 방치됌 -> 동일 파일 이름으로 이미지 업로드시 오류 유발

    @PostMapping()
    public ResponseEntity<HttpStatus> uploadImage(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        return imageService. ();
    }
}

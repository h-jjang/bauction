package com.hjjang.backend.domain.image.service;

import lombok.Getter;

@Getter
public enum Path {
    imageSavePath(System.getProperty("user.dir") + "/backend/src/main/resources/images/");

    private final String path;
    Path(String path) {
        this.path = path;
    }

}

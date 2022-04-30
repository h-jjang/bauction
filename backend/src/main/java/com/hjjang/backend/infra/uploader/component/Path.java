package com.hjjang.backend.infra.uploader.component;

import lombok.Getter;

@Getter
public enum Path {
    imageSavePath(System.getProperty("user.dir") + "/backend/src/main/resources/images/");

    private final String path;
    Path(String path) {
        this.path = path;
    }

}

package com.hjjang.backend.global.config.security.parser;


import com.hjjang.backend.domain.user.entity.ProviderType;

import java.util.Map;

public class ParsingUserContextFactory {
    public static ParsingUserContext getParsingParsingUserContext(ProviderType providerType, Map<String, Object> attributes) {
        if (providerType == ProviderType.KAKAO) {
            return new KakaoParsingParsingUserContext(attributes);
        }
        throw new IllegalArgumentException("Invalid Provider Type.");
    }
}

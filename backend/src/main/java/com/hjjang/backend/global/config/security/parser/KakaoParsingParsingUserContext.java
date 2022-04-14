package com.hjjang.backend.global.config.security.parser;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

import static com.hjjang.backend.global.config.security.parser.KakaoInfoProperties.*;

public class KakaoParsingParsingUserContext extends ParsingUserContext {

    public KakaoParsingParsingUserContext(Map<String, Object> attributes) {
        super(attributes);
    }

    // kakao response json spec
    // https://developers.kakao.com/docs/latest/ko/kakaologin/common#additional-consent
    @Override
    public String getId() {
        return attributes.get(ID.getCode()).toString();
    }

    @Override
    public String getName() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get(PROPERTIES.getCode());

        if (properties == null) {
            return null;
        }

        return (String) properties.get(NICKNAME.getCode());
    }

    @Override
    public String getEmail() {
        return (String) attributes.get(EMAIL.getCode());
    }

    @Override
    public String getImageUrl() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get(PROPERTIES.getCode());

        if (properties == null) {
            return null;
        }

        return (String) properties.get(PROPERTIES.getCode());
    }

}

@Getter
@AllArgsConstructor
enum KakaoInfoProperties {
    ID("id"),
    EMAIL("account_email"),
    NICKNAME("nickname"),
    PROFILE_IMAGE("thumbnail_image"),
    PROPERTIES("properties");

    private final String code;
}


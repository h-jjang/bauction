package com.hjjang.backend.global.security.parser;

import java.util.Map;

public abstract class ParsingUserContext {
    protected Map<String, Object> attributes;

    public ParsingUserContext(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();
}
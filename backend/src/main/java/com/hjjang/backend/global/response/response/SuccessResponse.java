package com.hjjang.backend.global.response.response;

import com.hjjang.backend.global.response.code.SuccessCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SuccessResponse {

    @Schema(description = "status code")
    private final int status;
    @Schema(description = "Business code")
    private final String code;
    @Schema(description = "response message")
    private final String message;
    @Schema(description = "response data")
    private final Object data;

    public static SuccessResponse of(SuccessCode successCode, Object data) {
        return new SuccessResponse(successCode, data);
    }

    public static SuccessResponse of(SuccessCode successCode) {
        return new SuccessResponse(successCode, "");
    }

    public SuccessResponse(SuccessCode successCode, Object data) {
        this.status = successCode.getStatus();
        this.code = successCode.getCode();
        this.message = successCode.getMessage();
        this.data = data;
    }
}

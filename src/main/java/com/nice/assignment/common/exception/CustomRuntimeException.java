package com.nice.assignment.common.exception;

import com.nice.assignment.common.response.ApiResponseCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class CustomRuntimeException extends RuntimeException {
    private ApiResponseCode responseCode;
    private Object data;
    private Boolean isReported = Boolean.TRUE;

    public CustomRuntimeException(ApiResponseCode responseCode) {
        this(responseCode, null);
    }

    public CustomRuntimeException(ApiResponseCode responseCode, Object data) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
        this.data = data;
    }

    public CustomRuntimeException(String message) {
        super(message);
        this.responseCode = ApiResponseCode.SERVER_ERROR;
    }

    public CustomRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}

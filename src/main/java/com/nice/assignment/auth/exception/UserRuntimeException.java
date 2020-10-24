package com.nice.assignment.auth.exception;

import com.nice.assignment.auth.responce.ApiResponseCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class UserRuntimeException extends RuntimeException {
    private ApiResponseCode responseCode;
    private Object data;
    private Boolean isReported = Boolean.TRUE;

    public UserRuntimeException(ApiResponseCode responseCode) {
        this(responseCode, null);
    }

    public UserRuntimeException(ApiResponseCode responseCode, Object data) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
        this.data = data;
    }

    public UserRuntimeException(String message) {
        super(message);
        this.responseCode = ApiResponseCode.SERVER_ERROR;
    }

    public UserRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}

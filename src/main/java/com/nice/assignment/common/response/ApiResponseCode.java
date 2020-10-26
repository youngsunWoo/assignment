package com.nice.assignment.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApiResponseCode implements ResponseCodeBase {
    SUCCESS(HttpStatus.OK, 200, "OK"),
    DATA_NOT_FOUND(HttpStatus.OK, 404, "DATA_NOT_FOUND"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, -500, "SERVER ERROR"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, -400, "INCORRECT REQUEST"),

    ;
    private final HttpStatus status;
    private final int code;
    private final String message;
}
